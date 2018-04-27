package in.clouthink.synergy.rbac.annotation.support;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import in.clouthink.synergy.rbac.model.*;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dz
 */
@Configuration
public class EnableResourceImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(EnableResource.class.getName(),
                                                                                       false);
        Object pluginId = attributes == null ? null : attributes.getFirst("resources");

        if (pluginId == null) {
            return new String[0];
        }
        return new String[]{ResourcePluginBeanRegistrar.class.getName()};
    }

    public static class ResourcePluginBeanRegistrar implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes enableMenu = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
                    EnableResource.class.getName(),
                    false));

            if (enableMenu != null) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DeclaredResourceProvider.class);
                AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

                MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
                mutablePropertyValues.add("name", metadata.getClassName());
                mutablePropertyValues.add("resources", toResourceList(enableMenu.getAnnotationArray("resources")));
                beanDefinition.setPropertyValues(mutablePropertyValues);

                //TODO confirm the annotated target class
                registry.registerBeanDefinition("DeclaredResourceProvider:" + metadata.getClassName(),
                                                beanDefinition);
            }
        }

        private List<Resource> toResourceList(AnnotationAttributes[] menu) {
            return Stream.of(menu).map(item -> toResource(item)).collect(Collectors.toList());
        }

        private Resource toResource(AnnotationAttributes menu) {
            DefaultResource result = new DefaultResource();
            result.setCode(menu.getString("code"));
            result.setName(menu.getString("name"));
            result.setExtraAttrs(toMetadata(menu.getAnnotationArray("metadata")));
            result.setPermissions(toPermissionSet(menu.getAnnotationArray("permissions")));
            return result;
        }

        private Set<Permission> toPermissionSet(AnnotationAttributes[] permissions) {
            if (permissions == null) {
                return new HashSet<>();
            }

            return Stream.of(permissions).map(item -> {
                String api = item.getString("api");
                Action[] actions = (Action[]) item.get("actions");

                return new DefaultPermission(api, actions);
            }).collect(Collectors.toSet());
        }

        private Map<String, Object> toMetadata(AnnotationAttributes[] metadatas) {
            if (metadatas == null) {
                return new HashMap<>();
            }

            return Stream.of(metadatas).collect(Collectors.toMap(item -> item.getString("key"), item -> {
                Metadata.ValueType valueType = item.getEnum("type");
                switch (valueType) {
                    case Boolean:
                        return item.getBoolean("value");
                    case Short:
                    case Integer:
                    case Long:
                        return item.getNumber("value");
                    case BigDecimal:
                        return new BigDecimal(item.getString("value"));
                    default:
                        return item.getString("value");
                }
            }));
        }

    }

}

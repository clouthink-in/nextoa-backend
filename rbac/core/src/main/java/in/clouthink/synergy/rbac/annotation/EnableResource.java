package in.clouthink.synergy.rbac.annotation;

import in.clouthink.synergy.rbac.annotation.support.EnableResourceImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enable the value plugin, it means it's a value plugin , can be registered into the repository by system automatically.
 *
 * @author dz
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableResourceImportSelector.class)
public @interface EnableResource {

    /**
     * @return the flatten value provided by the plugin
     */
    Resource[] value();

}

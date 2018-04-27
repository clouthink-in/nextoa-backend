package in.clouthink.synergy.rbac.support.memory;

import in.clouthink.synergy.rbac.model.Resource;
import in.clouthink.synergy.rbac.model.ResourceChild;
import in.clouthink.synergy.rbac.model.ResourceMatcher;
import in.clouthink.synergy.rbac.service.ResourceDiscovery;
import in.clouthink.synergy.rbac.service.ResourceRegistry;
import in.clouthink.synergy.rbac.spi.ResourceProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
public class ResourceMemoryDiscovery implements ResourceDiscovery, InitializingBean {

    private List<ResourceProvider> resourceProviderList = new ArrayList<>();

    private ResourceRegistry resourceRegistry = new ResourceMemoryRegistry();

    /**
     * The default object is ResourceMemoryRepository, replace it with your impl.
     *
     * @param resourceRegistry
     */
    public void setResourceRegistry(ResourceRegistry resourceRegistry) {
        this.resourceRegistry = resourceRegistry;
    }

    public List<ResourceProvider> getResourceProviderList() {
        return resourceProviderList;
    }

    /**
     * Please provide the value before the value service initialized.
     *
     * @param resourceProviderList
     */
    public void setResourceProviderList(List<ResourceProvider> resourceProviderList) {
        this.resourceProviderList = resourceProviderList;
    }

    @Override
    public String getHashcode() {
        return resourceRegistry.getHashcode();
    }

    @Override
    public Resource findByCode(String code) {
        return resourceRegistry.findByCode(code);
    }

    @Override
    public List<Resource> getRootResources() {
        return resourceRegistry.getRootResources();
    }

    @Override
    public List<Resource> getFlattenResources() {
        return resourceRegistry.getFlattenResources();
    }

    @Override
    public List<Resource> getResourceChildren(String resourceCode) {
        return resourceRegistry.getResourceChildren(resourceCode);
    }

    @Override
    public Resource getResourceParent(String resourceCode) {
        return resourceRegistry.getResourceParent(resourceCode);
    }

    @Override
    public Resource getFirstMatchedResource(ResourceMatcher matcher) {
        return resourceRegistry.getFirstMatchedResource(matcher);
    }

    @Override
    public List<Resource> getMatchedResources(ResourceMatcher matcher) {
        return resourceRegistry.getMatchedResources(matcher);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        resourceProviderList.stream()
                            .flatMap(provider -> provider.listResources().stream())
                            .map(r -> (ResourceChild) r)
                            .filter(r -> StringUtils.isEmpty(r.getParentCode()))
                            .forEach(resource -> resourceRegistry.addResource(resource));

        resourceProviderList.stream()
                            .flatMap(provider -> provider.listResources().stream())
                            .map(r -> (ResourceChild) r)
                            .filter(r -> !StringUtils.isEmpty(r.getParentCode()))
                            .forEach(resource -> resourceRegistry.addChildren(resource.getParentCode(), resource));
    }

}

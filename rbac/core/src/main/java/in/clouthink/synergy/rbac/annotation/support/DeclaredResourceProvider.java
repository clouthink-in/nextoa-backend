package in.clouthink.synergy.rbac.annotation.support;

import in.clouthink.synergy.rbac.model.Resource;
import in.clouthink.synergy.rbac.spi.ResourceProvider;

import java.util.Collections;
import java.util.List;

/**
 * The ResourceProvider impl
 *
 * @author dz
 */
public class DeclaredResourceProvider implements ResourceProvider {

    private String name;

    private List<Resource> resources = Collections.emptyList();

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = Collections.unmodifiableList(resources);
    }

    @Override
    public List<Resource> listResources() {
        return resources;
    }

}

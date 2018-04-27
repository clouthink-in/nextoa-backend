package in.clouthink.synergy.rbac.rest.support.mock;

import in.clouthink.synergy.rbac.rest.view.*;
import in.clouthink.synergy.rbac.rest.support.ResourceRestSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRestSupportMocker implements ResourceRestSupport {

    @Override
    public List<ResourceView> listFlattenResources() {
        return null;
    }

    @Override
    public List<ResourceTreeView> listHierarchyResources() {
        return null;
    }

    @Override
    public ResourceDetailView getResourceDetailView(String code) {
        return null;
    }

}

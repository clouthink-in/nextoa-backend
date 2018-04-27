package in.clouthink.synergy.rbac.rest.support;


import in.clouthink.synergy.rbac.rest.view.ResourceDetailView;
import in.clouthink.synergy.rbac.rest.view.ResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.ResourceView;

import java.util.List;

/**
 */
public interface ResourceRestSupport {

    /**
     * @return flatten value list
     */
    List<ResourceView> listFlattenResources();

    /**
     * @return the root value in tree
     */
    List<ResourceTreeView> listHierarchyResources();

    /**
     * @param code
     * @return the value detail (with permission)
     */
    ResourceDetailView getResourceDetailView(String code);
}

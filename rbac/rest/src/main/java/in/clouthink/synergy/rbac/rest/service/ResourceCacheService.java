package in.clouthink.synergy.rbac.rest.service;

import in.clouthink.synergy.rbac.rest.view.ResourceDetailView;
import in.clouthink.synergy.rbac.rest.view.ResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.ResourceView;

import java.util.List;


/**
 * The cached value service
 *
 * @author dz
 */
public interface ResourceCacheService {

    /**
     * @return the value of tree struct and the root level of the tree is returned
     */
    List<ResourceTreeView> listHierarchyResources();

    /**
     * @param cached true, return the cached tree , otherwise, query and return the latest tree
     * @return
     */
    List<ResourceTreeView> listHierarchyResources(boolean cached);

    /**
     * @return the value of flatten list, but the parent-children struct is kept in view which can be re-org in tree in the client-side.
     */
    List<ResourceView> listFlattenResources();

    /**
     * @param cached true, return the cached list , otherwise, query and return the latest list
     * @return
     */
    List<ResourceView> listFlattenResources(boolean cached);

    /**
     * @param code
     * @return
     */
    ResourceDetailView getResourceDetail(String code);

}

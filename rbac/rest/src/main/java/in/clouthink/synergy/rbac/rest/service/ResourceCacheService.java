package in.clouthink.synergy.rbac.rest.service;

import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;

import java.util.List;

/**
 * @author dz
 */
public interface ResourceCacheService {

	List<PrivilegedResourceWithChildrenView> listResources();

	List<PrivilegedResourceWithChildrenView> listResources(boolean cached);

}

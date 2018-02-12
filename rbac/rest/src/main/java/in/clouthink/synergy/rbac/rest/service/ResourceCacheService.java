package in.clouthink.synergy.rbac.rest.service;

import in.clouthink.synergy.rbac.rest.dto.PrivilegedResourceWithChildren;

import java.util.List;

/**
 * @author dz
 */
public interface ResourceCacheService {

	List<PrivilegedResourceWithChildren> listResources();

	List<PrivilegedResourceWithChildren> listResources(boolean cached);

}

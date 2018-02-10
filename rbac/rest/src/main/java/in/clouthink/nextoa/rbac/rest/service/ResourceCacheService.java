package in.clouthink.nextoa.rbac.rest.service;

import in.clouthink.nextoa.rbac.rest.dto.PrivilegedResourceWithChildren;

import java.util.List;

/**
 * @author dz
 */
public interface ResourceCacheService {

	List<PrivilegedResourceWithChildren> listResources();

	List<PrivilegedResourceWithChildren> listResources(boolean cached);

}

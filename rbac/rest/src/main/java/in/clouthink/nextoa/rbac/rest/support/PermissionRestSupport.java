package in.clouthink.nextoa.rbac.rest.support;


import in.clouthink.nextoa.rbac.impl.model.TypedRole;
import in.clouthink.nextoa.rbac.rest.dto.GrantResourceParameter;
import in.clouthink.nextoa.rbac.rest.dto.PrivilegedResourceWithChildren;

import java.util.List;

/**
 */
public interface PermissionRestSupport {

	/**
	 * @param typedRoleCode
	 * @return
	 */
	List<PrivilegedResourceWithChildren> listGrantedResources(String typedRoleCode);

	/**
	 * @param resourceCode
	 * @return
	 */
	List<TypedRole> listGrantedRoles(String resourceCode);

	/**
	 * @param typedRoleCode
	 * @param grantRequest
	 */
	void grantResourcesToRole(String typedRoleCode, GrantResourceParameter grantRequest);

	/**
	 * @param typedRoleCode
	 * @param resourceCode
	 */
	void revokeResourcesFromRole(String typedRoleCode, String resourceCode);
}

package in.clouthink.synergy.rbac.rest.support;


import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.rest.param.GrantResourceParam;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;

import java.util.List;

/**
 */
public interface PermissionRestSupport {

	/**
	 * @param typedRoleCode
	 * @return
	 */
	List<PrivilegedResourceWithChildrenView> listGrantedResources(String typedRoleCode);

	/**
	 * @param resourceCode
	 * @return
	 */
	List<TypedRole> listGrantedRoles(String resourceCode);

	/**
	 * @param typedRoleCode
	 * @param grantRequest
	 */
	void grantResourcesToRole(String typedRoleCode, GrantResourceParam grantRequest);

	/**
	 * @param typedRoleCode
	 * @param resourceCode
	 */
	void revokeResourcesFromRole(String typedRoleCode, String resourceCode);
}

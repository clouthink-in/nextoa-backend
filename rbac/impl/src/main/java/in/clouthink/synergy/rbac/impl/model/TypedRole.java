package in.clouthink.synergy.rbac.impl.model;

import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.rbac.model.DefaultRole;

/**
 */
public class TypedRole extends DefaultRole {

	public static TypedRole newSysRole() {
		TypedRole result = new TypedRole();
		result.setRoleType(RoleType.SYS_ROLE);
		return result;
	}

	public static TypedRole newAppRole() {
		TypedRole result = new TypedRole();
		result.setRoleType(RoleType.APP_ROLE);
		return result;
	}

	public static boolean isSysRole(String roleType) {
		return RoleType.SYS_ROLE.name().equalsIgnoreCase(roleType);
	}

	public static boolean isAppRole(String roleType) {
		return RoleType.APP_ROLE.name().equalsIgnoreCase(roleType);
	}

	private RoleType roleType;

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}

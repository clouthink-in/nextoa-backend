package in.clouthink.synergy.rbac.impl.model;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.model.Roles;
import org.springframework.security.core.GrantedAuthority;

/**
 * @auther dz
 */
public final class TypedRoles {

    public static TypedRole from(GrantedAuthority authority) {
        if (authority instanceof Role) {
            Role role = (Role) authority;
            if (Roles.isSysRole(role)) {
                TypedRole typedRole = TypedRoles.newSysRole();
                typedRole.setCode(authority.getAuthority());
                typedRole.setName(((Role) authority).getName());
                return typedRole;
            }

            TypedRole typedRole = TypedRoles.newAppRole();
            typedRole.setCode(authority.getAuthority());
            typedRole.setName(((Role) authority).getName());
            return typedRole;
        }
        return null;
    }

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

}

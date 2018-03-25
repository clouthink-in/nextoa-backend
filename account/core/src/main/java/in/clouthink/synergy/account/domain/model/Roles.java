package in.clouthink.synergy.account.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

/**
 * The builtin three roles are defined as constants:
 * <p>
 * <ul>
 * <li>ADMIN</li>
 * <li>MGR</li>
 * <li>USER</li>
 * </ul>
 *
 * @auther dz
 */
public final class Roles {

    public static String ROLE_PREFIX = "ROLE_";

    //应用扩展（用户自定义）角色不能使用以下编码（内置角色已经占用）
    public static String ADMIN_ROLE_NAME = "ADMIN";
    public static String MGR_ROLE_NAME = "MGR";
    public static String USER_ROLE_NAME = "USER";

    public static GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority(ROLE_PREFIX + ADMIN_ROLE_NAME);
    public static GrantedAuthority ROLE_MGR = new SimpleGrantedAuthority(ROLE_PREFIX + MGR_ROLE_NAME);
    public static GrantedAuthority ROLE_USER = new SimpleGrantedAuthority(ROLE_PREFIX + USER_ROLE_NAME);

    /**
     * @return 系统内置角色
     */
    public static List<Role> initialize() {
        Role adminRole = new Role();
        adminRole.setCode(Roles.ADMIN_ROLE_NAME);
        adminRole.setName("超级管理员");
        adminRole.setType(RoleType.SYS_ROLE);

        Role mgrRole = new Role();
        mgrRole.setCode(Roles.MGR_ROLE_NAME);
        mgrRole.setName("管理员");
        mgrRole.setType(RoleType.SYS_ROLE);

        Role userRole = new Role();
        userRole.setCode(Roles.USER_ROLE_NAME);
        userRole.setName("普通用户");
        userRole.setType(RoleType.SYS_ROLE);

        return Arrays.asList(adminRole, mgrRole, userRole);
    }

    /**
     * 不能使用系统角色编码
     *
     * @param roleCode
     * @return
     */
    public static final boolean isIllegal(String roleCode) {
        return ADMIN_ROLE_NAME.equalsIgnoreCase(roleCode) ||
                MGR_ROLE_NAME.equalsIgnoreCase(roleCode) ||
                USER_ROLE_NAME.equalsIgnoreCase(roleCode);
    }

    public static final boolean isSysRole(Role role) {
        return (role != null) ? RoleType.SYS_ROLE == role.getType() : false;
    }

    public static final boolean isAppRole(Role role) {
        return (role != null) ? RoleType.APP_ROLE == role.getType() : false;
    }

    public static final String resolveRoleCode(GrantedAuthority grantedAuthority) {
        String authority = grantedAuthority.getAuthority();

        if (authority.startsWith(Roles.ROLE_PREFIX)) {
            authority = authority.substring(Roles.ROLE_PREFIX.length());
        }

        return authority;
    }

}

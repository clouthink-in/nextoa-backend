package in.clouthink.synergy.account.domain.model;

import java.util.Arrays;
import java.util.List;

/**
 * @auther dz
 */
public final class Roles {

    //应用扩展（用户自定义）角色不能使用以下编码（内置角色已经占用）
    public static String SYS_ROLE_NAME_ADMIN = "ADMIN";
    public static String SYS_ROLE_NAME_MGR = "MGR";
    public static String SYS_ROLE_NAME_USER = "USER";

    /**
     * @return 系统内置角色
     */
    public static List<Role> initialize() {
        Role adminRole = new Role();
        adminRole.setCode(Roles.SYS_ROLE_NAME_ADMIN);
        adminRole.setCode("超级管理员");
        adminRole.setType(RoleType.SYS_ROLE);

        Role mgrRole = new Role();
        mgrRole.setCode(Roles.SYS_ROLE_NAME_MGR);
        mgrRole.setCode("管理员");
        mgrRole.setType(RoleType.SYS_ROLE);

        Role userRole = new Role();
        userRole.setCode(Roles.SYS_ROLE_NAME_USER);
        userRole.setCode("普通用户");
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
        return SYS_ROLE_NAME_ADMIN.equalsIgnoreCase(roleCode) ||
                SYS_ROLE_NAME_MGR.equalsIgnoreCase(roleCode) ||
                SYS_ROLE_NAME_USER.equalsIgnoreCase(roleCode);
    }

}

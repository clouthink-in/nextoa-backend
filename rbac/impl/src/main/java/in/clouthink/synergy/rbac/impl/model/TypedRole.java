package in.clouthink.synergy.rbac.impl.model;

import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.rbac.model.DefaultRole;

/**
 */
public class TypedRole extends DefaultRole {

    private RoleType roleType;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}

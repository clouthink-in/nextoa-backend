package in.clouthink.synergy.rbac.impl.model;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.rbac.model.DefaultRole;
import org.springframework.security.core.GrantedAuthority;

/**
 */
public class TypedRole extends DefaultRole {

    public static TypedRole from(GrantedAuthority authority) {
        if (authority instanceof Role) {
            TypedRole typedRole = new TypedRole();
            typedRole.setCode(authority.getAuthority());
            typedRole.setName(((Role) authority).getName());
            typedRole.setType(((Role) authority).getType());
            return typedRole;
        }
        return null;
    }

    private RoleType type;

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

}

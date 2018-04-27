package in.clouthink.synergy.rbac.model;

/**
 * @auther dz
 */
public class DefaultPrivilege implements Privilege {

    Role role;

    Resource resource;

    public DefaultPrivilege() {
    }

    public DefaultPrivilege(Role role, Resource resource) {
        this.role = role;
        this.resource = resource;
    }

    @Override
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}

package in.clouthink.synergy.rbac.model;

/**
 * @auther dz
 */
public interface Privilege {

    static Privilege create(Role role, Resource resource) {
        return new DefaultPrivilege(role, resource);
    }

    Role getRole();

    Resource getResource();

}

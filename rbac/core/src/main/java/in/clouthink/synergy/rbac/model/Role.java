package in.clouthink.synergy.rbac.model;

/**
 * The role definition.
 */
public interface Role {

    /**
     * @return the role code (unique in global)
     */
    String getCode();

    /**
     * @return the role name (unique in global)
     */
    String getName();

}

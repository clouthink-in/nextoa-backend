package in.clouthink.synergy.account.domain.request;

/**
 * The request to save the role data
 */
public interface SaveRoleRequest {

    /**
     * Always return the upper cased code , and the code must be unique
     *
     * @return the code of the role
     */
    String getCode();

    /**
     * @return the name of the role
     */
    String getName();

    /**
     * @return the description of the role
     */
    String getDescription();

}

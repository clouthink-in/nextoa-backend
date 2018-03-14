package in.clouthink.synergy.account.domain.request;

/**
 * The request to save group with code & name attrs.
 *
 * @author dz
 */
public interface SaveGroupRequest {

    /**
     * @return the code of group
     */
    String getCode();

    /**
     * @return the name of group
     */
    String getName();

}

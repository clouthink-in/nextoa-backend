package in.clouthink.synergy.rbac.model;

import java.util.Map;

/**
 * The value represents one point on user-interface.
 */
public interface Resource extends PermissionSet {

    /**
     * The type expression can present what the value belongs to .
     * For example:
     * menu:portal -> the menu for portal
     * api:user -> the api for user
     *
     * @return
     */
    String getType();

    /**
     * The value code must be unique in global (include the children)
     *
     * @return
     */
    String getCode();

    /**
     * The value name must be unique in same level
     *
     * @return
     */
    String getName();

    /**
     * The extra metadata for the value.
     * for example : the icon to present the value
     *
     * @return
     */
    Map<String, Object> getExtraAttrs();

}

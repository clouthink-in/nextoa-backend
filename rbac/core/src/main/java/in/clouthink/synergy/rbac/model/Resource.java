package in.clouthink.synergy.rbac.model;

import java.util.Map;

/**
 * The resource represents one point on user-interface.
 */
public interface Resource extends PermissionSet {

    /**
     * The type expression can present what the resource belongs to .
     * For example:
     * menu:portal -> the menu for portal
     * api:user -> the api for user
     *
     * @return
     */
    String getType();

    /**
     * The resource code must be unique in global (include the children)
     *
     * @return
     */
    String getCode();

    /**
     * The resource name must be unique in same level
     *
     * @return
     */
    String getName();

    /**
     * The extra metadata for the resource.
     * for example : the icon to present the resource
     *
     * @return
     */
    Map<String, Object> getExtraAttrs();

}

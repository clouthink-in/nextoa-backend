package in.clouthink.synergy.rbac.model;

import java.util.Map;

/**
 * The resource represents entry on user-interface.
 */
public interface Resource extends PermissionSet {

    /**
     * The type expression can present what category the resource belongs to .
     * For example:
     * ui -> the ui elements
     * api -> the api
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

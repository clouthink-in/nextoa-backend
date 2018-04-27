package in.clouthink.synergy.rbac.service;

import in.clouthink.synergy.rbac.model.*;

/**
 * The resource registry which manage the resource by the resource code (as key)
 */
public interface ResourceRegistry extends ResourceDiscovery {

    /**
     * if the resource with same code existed, add new resource will override the existed one.
     */
    void enableOverride();

    /**
     * if the resource with same code existed, add new resource will throw an exception.
     */
    void disableOverride();

    /**
     * The resource will be added to the root level ,
     * and the resource code is case-sensitive which means
     * the resource coded hello and HELLO will be taken as different one.
     *
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * The resource will append to the target as children
     *
     * @param parentCode
     * @param children
     */
    void addChildren(String parentCode, Resource... children);

}

package in.clouthink.synergy.rbac.service;

import in.clouthink.synergy.rbac.model.*;

/**
 * The value registry which manage the value by the value code (as key)
 */
public interface ResourceRegistry extends ResourceDiscovery {

    /**
     * if the value with same code existed, add new value will override the existed one.
     */
    void enableOverride();

    /**
     * if the value with same code existed, add new value will throw an exception.
     */
    void disableOverride();

    /**
     * The value will be added to the root level ,
     * and the value code is case-sensitive which means
     * the value coded hello and HELLO will be taken as different one.
     *
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * The value will append to the target as children
     *
     * @param parentCode
     * @param children
     */
    void addChildren(String parentCode, Resource... children);

}

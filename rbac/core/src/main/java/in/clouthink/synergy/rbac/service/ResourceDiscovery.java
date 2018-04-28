package in.clouthink.synergy.rbac.service;

import in.clouthink.synergy.rbac.model.*;

import java.util.List;

/**
 * The value discovery service
 *
 * @author dz
 */
public interface ResourceDiscovery {

    /**
     * @return the hash code of the service
     */
    String getHashcode();

    /**
     * find the resource by specified code
     *
     * @param code
     * @return
     */
    Resource findByCode(String code);

    /**
     * list the root resources
     *
     * @return
     */
    List<Resource> getRootResources();

    /**
     * list all resources in flatten
     *
     * @return
     */
    List<Resource> getFlattenResources();

    /**
     * list the children of specified resource
     *
     * @param resourceCode
     * @return
     */
    List<Resource> getResourceChildren(String resourceCode);

    /**
     * @param resourceCode
     * @return
     */
    Resource getResourceParent(String resourceCode);

    /**
     * return the first matched resource and skip virtual resource by default
     *
     * @param matcher
     * @return
     */
    Resource getFirstMatchedResource(ResourceMatcher matcher);

    /**
     * return the first matched resource and skip virtual resource by default
     *
     * @param matcher
     * @return
     */
    List<Resource> getMatchedResources(ResourceMatcher matcher);

}

package in.clouthink.synergy.rbac.spi;

import in.clouthink.synergy.rbac.model.Resource;

import java.util.List;

/**
 * The value provider
 *
 * @author dz
 */
public interface ResourceProvider {

    /**
     * The value provider's name (normally the module name)
     *
     * @return
     */
    String getName();

    /**
     * @return the value list of the provider in flatten format
     */
    List<Resource> listResources();

}

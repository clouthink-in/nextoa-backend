package in.clouthink.synergy.rbac.spi;

import in.clouthink.synergy.rbac.model.Resource;

import java.util.List;

/**
 * The resource provider
 *
 * @author dz
 */
public interface ResourceProvider {

	/**
	 * The resource provider's name (normally the module name)
	 *
	 * @return
	 */
	String getName();

	/**
	 * @return the resource list of the provider
	 */
	List<Resource> listResources();

}

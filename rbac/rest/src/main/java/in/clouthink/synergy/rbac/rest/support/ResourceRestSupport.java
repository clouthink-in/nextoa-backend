package in.clouthink.synergy.rbac.rest.support;


import in.clouthink.synergy.rbac.rest.dto.PrivilegedResourceWithChildren;

import java.util.List;

/**
 */
public interface ResourceRestSupport {

	/**
	 * @return
	 */
	List<PrivilegedResourceWithChildren> listResources();

}

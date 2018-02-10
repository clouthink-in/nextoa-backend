package in.clouthink.nextoa.rbac.rest.support;


import in.clouthink.nextoa.rbac.rest.dto.PrivilegedResourceWithChildren;

import java.util.List;

/**
 */
public interface ResourceRestSupport {

	/**
	 * @return
	 */
	List<PrivilegedResourceWithChildren> listResources();

}

package in.clouthink.synergy.rbac.rest.support;


import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;

import java.util.List;

/**
 */
public interface ResourceRestSupport {

	/**
	 * @return
	 */
	List<PrivilegedResourceWithChildrenView> listResources();

}

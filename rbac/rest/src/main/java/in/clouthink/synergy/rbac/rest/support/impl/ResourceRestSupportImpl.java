package in.clouthink.synergy.rbac.rest.support.impl;

import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;
import in.clouthink.synergy.rbac.rest.service.ResourceCacheService;
import in.clouthink.synergy.rbac.rest.support.ResourceRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRestSupportImpl implements ResourceRestSupport {

	@Autowired
	private ResourceCacheService resourceService;

	@Override
	public List<PrivilegedResourceWithChildrenView> listResources() {
		return resourceService.listResources();
	}

}

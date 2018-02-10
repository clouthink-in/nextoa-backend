package in.clouthink.nextoa.rbac.rest.support.mock;

import in.clouthink.nextoa.rbac.rest.dto.PrivilegedResourceWithChildren;
import in.clouthink.nextoa.rbac.rest.support.ResourceRestSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dz
 */
@Component
public class ResourceRestSupportMockImpl implements ResourceRestSupport {
	@Override
	public List<PrivilegedResourceWithChildren> listResources() {
		return null;
	}
}

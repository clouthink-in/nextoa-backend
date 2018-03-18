package in.clouthink.synergy.rbac.rest.support.mock;

import in.clouthink.synergy.rbac.rest.dto.PrivilegedResourceWithChildren;
import in.clouthink.synergy.rbac.rest.support.ResourceRestSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRestSupportMocker implements ResourceRestSupport {

    @Override
    public List<PrivilegedResourceWithChildren> listResources() {
        return null;
    }

}

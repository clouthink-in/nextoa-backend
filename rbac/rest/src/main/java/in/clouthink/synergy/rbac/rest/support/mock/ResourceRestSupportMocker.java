package in.clouthink.synergy.rbac.rest.support.mock;

import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceWithChildrenView;
import in.clouthink.synergy.rbac.rest.support.ResourceRestSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRestSupportMocker implements ResourceRestSupport {

    @Override
    public List<PrivilegedResourceWithChildrenView> listResources() {
        return null;
    }

}

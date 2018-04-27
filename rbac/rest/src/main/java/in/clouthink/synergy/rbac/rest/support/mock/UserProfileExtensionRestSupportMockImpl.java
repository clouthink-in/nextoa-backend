package in.clouthink.synergy.rbac.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.rbac.rest.support.UserProfileExtensionRestSupport;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserProfileExtensionRestSupport mocker
 *
 * @author dz
 */
@Component
public class UserProfileExtensionRestSupportMockImpl implements UserProfileExtensionRestSupport {

    @Override
    public List<ResourceView> getGrantedResources(User user) {
        return null;
    }

}

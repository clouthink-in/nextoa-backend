package in.clouthink.synergy.rbac.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.rbac.rest.view.ResourceView;

import java.util.List;

/**
 * @author dz
 */
public interface UserProfileExtensionRestSupport {

    /**
     * @param user
     * @return granted resources
     */
    List<ResourceView> getGrantedResources(User user);

}

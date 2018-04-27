package in.clouthink.synergy.rbac.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.rbac.rest.support.UserProfileExtensionRestSupport;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import in.clouthink.synergy.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProfileExtensionRestSupportImpl implements UserProfileExtensionRestSupport {

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<ResourceView> getGrantedResources(User user) {
//        return permissionService.getGrantedResources((List) user.getAuthorities())
//                                .stream()
//                                .map(r -> ResourceView.from(r))
//                                .collect(Collectors.toList());

        return null;
    }

}

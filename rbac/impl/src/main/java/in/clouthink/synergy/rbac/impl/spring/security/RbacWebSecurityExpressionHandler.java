package in.clouthink.synergy.rbac.impl.spring.security;

import in.clouthink.synergy.rbac.service.PermissionService;
import in.clouthink.synergy.rbac.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * How to configure
 * <p>
 * http.authorizeRequests()
 * .accessDecisionManager(accessDecisionManager())
 * .antMatchers("put the wanted url here")
 * .access("passRbacCheck")
 * <p>
 *
 * @author dz
 */
public class RbacWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
                                                                     FilterInvocation fi) {
        RbacWebSecurityExpressionRoot root = new RbacWebSecurityExpressionRoot(authentication,
                                                                               fi,
                                                                               permissionService,
                                                                               resourceService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }

}

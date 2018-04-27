package in.clouthink.synergy.rbac.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.rbac.rest.support.UserProfileExtensionRestSupport;
import in.clouthink.synergy.rbac.rest.view.ResourceView;
import in.clouthink.synergy.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 对 UserProfileRestController 的扩展,增加了获取可访问资料列表
 */
@Api(value = "/my/resources", description = "我的个人资料")
@RestController
@RequestMapping("/api/my/resources")
public class UserProfileExtensionRestController {

    @Autowired
    private UserProfileExtensionRestSupport userProfileExtensionRestSupport;

    @ApiOperation(value = "查看我已授权的页面操作")
    @GetMapping()
    public List<ResourceView> getGrantedResources() {
        User user = (User) SecurityContexts.getContext().requireUser();
        return userProfileExtensionRestSupport.getGrantedResources(user);
    }

}

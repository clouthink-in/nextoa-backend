package in.clouthink.synergy.account.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.param.ChangeMyPasswordParam;
import in.clouthink.synergy.account.rest.param.ChangeMyProfileParam;
import in.clouthink.synergy.account.rest.view.UserProfileView;
import in.clouthink.synergy.account.rest.support.UserProfileRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/my", description = "我的个人资料")
@RestController
@RequestMapping("/api/my")
public class UserProfileRestController {

    @Autowired
    private UserProfileRestSupport currentUserProfileRestSupport;

//	@Autowired
//	private StorageService attachmentService;

    @ApiOperation(value = "查看我的个人资料")
    @GetMapping(value = "/profile")
    public UserProfileView getUserProfile() {
        User user = (User) SecurityContexts.getContext().requireUser();
        return currentUserProfileRestSupport.getUserProfile(user);
    }

    @ApiOperation(value = "修改我的个人资料")
    @PostMapping(value = "/profile")
    public void updateUserProfile(@RequestBody ChangeMyProfileParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        currentUserProfileRestSupport.updateUserProfile(request, user);
    }

    @ApiOperation(value = "修改我的头像信息")
    @PostMapping(value = "/avatar")
    public void updateUserAvatar(@RequestBody String imageId) {
        User user = (User) SecurityContexts.getContext().requireUser();
        currentUserProfileRestSupport.updateUserAvatar(imageId, user);
    }

    @ApiOperation(value = "删除我的头像信息")
    @DeleteMapping(value = "/avatar")
    public void deleteUserAvatar() {
        User user = (User) SecurityContexts.getContext().requireUser();
        currentUserProfileRestSupport.updateUserAvatar(null, user);
    }

    @ApiOperation(value = "修改我的密码")
    @PostMapping(value = "/password")
    public void changeMyPassword(@RequestBody ChangeMyPasswordParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        currentUserProfileRestSupport.changeMyPassword(request, user);
    }

}

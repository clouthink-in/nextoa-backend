package in.clouthink.synergy.setting.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.setting.rest.param.SaveSystemSettingParam;
import in.clouthink.synergy.setting.rest.view.SystemSettingView;
import in.clouthink.synergy.setting.rest.support.SystemSettingRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/system-settings", description = "系统设置")
@RestController
@RequestMapping()
public class SystemSettingRestController {

    @Autowired
    private SystemSettingRestSupport systemSettingRestSupport;

    @ApiOperation(value = "查询系统设置")
    @GetMapping(value = {"/api/system-settings", "/guest/system-settings"})
    public SystemSettingView getSystemSetting() {
        return systemSettingRestSupport.getSystemSetting();
    }

    @ApiOperation(value = "修改系统设置")
    @PostMapping(value = "/api/system-settings")
    public void updateSystemSetting(@RequestBody SaveSystemSettingParam updateSystemSetting) {
        User user = (User) SecurityContexts.getContext().requireUser();
        systemSettingRestSupport.updateSystemSetting(updateSystemSetting, user);
    }

}

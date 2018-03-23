package in.clouthink.synergy.setting.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.setting.rest.param.SaveSystemSettingParam;
import in.clouthink.synergy.setting.rest.view.SystemSettingView;
import in.clouthink.synergy.setting.rest.support.SystemSettingRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("系统设置")
@RestController
@RequestMapping()
public class SystemSettingRestController {

	@Autowired
	private SystemSettingRestSupport systemSettingRestSupport;

	@ApiOperation(value = "查询系统设置")
	@RequestMapping(value = {"/api/settings/system", "/guest/settings/system"}, method = RequestMethod.GET)
	public SystemSettingView getSystemSetting() {
		return systemSettingRestSupport.getSystemSetting();
	}

	@ApiOperation(value = "修改系统设置")
	@RequestMapping(value = "/api/settings/system", method = RequestMethod.POST)
	public void updateSystemSetting(@RequestBody SaveSystemSettingParam updateSystemSetting) {
		User user = (User) SecurityContexts.getContext().requireUser();
		systemSettingRestSupport.updateSystemSetting(updateSystemSetting, user);
	}

}

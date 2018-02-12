package in.clouthink.synergy.setting.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.synergy.setting.rest.dto.SystemSettingSummary;
import in.clouthink.synergy.setting.rest.support.SystemSettingRestSupport;
import in.clouthink.synergy.setting.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SystemSettingRestSupportImpl implements SystemSettingRestSupport {

	@Autowired
	private SystemSettingService systemSettingService;

	@Override
	public SystemSettingSummary getSystemSetting() {
		return SystemSettingSummary.from(systemSettingService.getSystemSetting());
	}

	@Override
	public void updateSystemSetting(SaveSystemSettingRequest systemSetting, User byWho) {
		systemSettingService.saveSystemSetting(systemSetting, byWho);
	}

}

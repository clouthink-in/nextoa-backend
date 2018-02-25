package in.clouthink.synergy.setting.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.setting.domain.model.SystemSetting;
import in.clouthink.synergy.setting.domain.request.SaveSystemSettingRequest;

/**
 * @author dz
 */
public interface SystemSettingService {

	SystemSetting getSystemSetting();

	void saveSystemSetting(SaveSystemSettingRequest systemSetting, User byWho);

}

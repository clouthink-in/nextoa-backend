package in.clouthink.nextoa.setting.service;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.setting.domain.model.SystemSetting;
import in.clouthink.nextoa.setting.domain.request.SaveSystemSettingRequest;

/**
 * @author dz
 */
public interface SystemSettingService {

	SystemSetting getSystemSetting();

	void saveSystemSetting(SaveSystemSettingRequest systemSetting, User byWho);

}

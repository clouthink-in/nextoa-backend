package in.clouthink.nextoa.setting.rest.support;


import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.nextoa.setting.rest.dto.SystemSettingSummary;

/**
 * @author dz
 */
public interface SystemSettingRestSupport {

	SystemSettingSummary getSystemSetting();

	void updateSystemSetting(SaveSystemSettingRequest updateSystemSetting, User byWho);

}

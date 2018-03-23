package in.clouthink.synergy.setting.rest.support;


import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.synergy.setting.rest.view.SystemSettingView;

/**
 * @author dz
 */
public interface SystemSettingRestSupport {

	SystemSettingView getSystemSetting();

	void updateSystemSetting(SaveSystemSettingRequest updateSystemSetting, User byWho);

}

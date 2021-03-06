package in.clouthink.synergy.setting.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.synergy.setting.rest.view.SystemSettingView;
import in.clouthink.synergy.setting.rest.support.SystemSettingRestSupport;
import org.springframework.stereotype.Component;

/**
 * SystemSettingRestSupport mocker
 *
 * @author dz
 */
@Component
public class SystemSettingRestSupportMockImpl implements SystemSettingRestSupport {

	@Override
	public SystemSettingView getSystemSetting() {
		return null;
	}

	@Override
	public void updateSystemSetting(SaveSystemSettingRequest updateSystemSetting, User byWho) {

	}
}

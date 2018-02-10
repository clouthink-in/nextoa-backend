package in.clouthink.nextoa.setting.rest.support.mock;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.nextoa.setting.rest.dto.SystemSettingSummary;
import in.clouthink.nextoa.setting.rest.support.SystemSettingRestSupport;
import org.springframework.stereotype.Component;

/**
 * SystemSettingRestSupport mocker
 *
 * @author dz
 */
@Component
public class SystemSettingRestSupportMockImpl implements SystemSettingRestSupport {

	@Override
	public SystemSettingSummary getSystemSetting() {
		return null;
	}

	@Override
	public void updateSystemSetting(SaveSystemSettingRequest updateSystemSetting, User byWho) {

	}
}

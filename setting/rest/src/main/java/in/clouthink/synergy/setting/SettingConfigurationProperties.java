package in.clouthink.synergy.setting;

import in.clouthink.synergy.setting.rest.param.SaveSystemSettingParam;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dz
 */
@ConfigurationProperties(prefix = "synergy.setting")
public class SettingConfigurationProperties {

	private SaveSystemSettingParam system = new SaveSystemSettingParam();

	public SaveSystemSettingParam getSystem() {
		return system;
	}

	public void setSystem(SaveSystemSettingParam system) {
		this.system = system;
	}

}

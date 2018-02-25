package in.clouthink.synergy.setting;

import in.clouthink.synergy.setting.rest.dto.SaveSystemSettingParameter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dz
 */
@ConfigurationProperties(prefix = "synergy.setting")
public class SettingConfigurationProperties {

	private SaveSystemSettingParameter system = new SaveSystemSettingParameter();

	public SaveSystemSettingParameter getSystem() {
		return system;
	}

	public void setSystem(SaveSystemSettingParameter system) {
		this.system = system;
	}

}

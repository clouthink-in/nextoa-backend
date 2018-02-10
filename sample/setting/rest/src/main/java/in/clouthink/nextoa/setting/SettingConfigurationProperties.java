package in.clouthink.nextoa.setting;

import in.clouthink.nextoa.setting.rest.dto.SaveSystemSettingParameter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dz
 */
@ConfigurationProperties(prefix = "in.clouthink.nextoa.setting")
public class SettingConfigurationProperties {

	private SaveSystemSettingParameter system = new SaveSystemSettingParameter();

	public SaveSystemSettingParameter getSystem() {
		return system;
	}

	public void setSystem(SaveSystemSettingParameter system) {
		this.system = system;
	}

}

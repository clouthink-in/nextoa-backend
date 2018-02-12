package in.clouthink.synergy.setting;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.setting.rest.controller", "in.clouthink.synergy.setting.rest.support.mock"})
public class SystemSettingMockModuleConfiguration {

}

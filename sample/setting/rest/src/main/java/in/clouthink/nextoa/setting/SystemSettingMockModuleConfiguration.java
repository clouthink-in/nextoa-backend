package in.clouthink.nextoa.setting;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.setting.rest.controller", "in.clouthink.nextoa.setting.rest.support.mock"})
public class SystemSettingMockModuleConfiguration {

}

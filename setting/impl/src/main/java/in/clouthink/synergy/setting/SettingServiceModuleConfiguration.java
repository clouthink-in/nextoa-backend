package in.clouthink.synergy.setting;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.setting.service"})
@EnableMongoRepositories({"in.clouthink.synergy.setting.repository"})
public class SettingServiceModuleConfiguration {

}

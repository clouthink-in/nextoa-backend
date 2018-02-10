package in.clouthink.nextoa.setting;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.setting.service"})
@EnableMongoRepositories({"in.clouthink.nextoa.setting.repository"})
public class SettingServiceModuleConfiguration {

}

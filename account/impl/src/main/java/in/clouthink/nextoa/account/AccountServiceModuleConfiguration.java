package in.clouthink.nextoa.account;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.account.service", "in.clouthink.nextoa.account.repository"})
@EnableMongoRepositories({"in.clouthink.nextoa.account.repository"})
@EnableConfigurationProperties(AccountPasswordConfigurationProperties.class)
public class AccountServiceModuleConfiguration {

}

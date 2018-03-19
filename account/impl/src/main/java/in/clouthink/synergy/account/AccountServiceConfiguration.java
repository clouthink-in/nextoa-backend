package in.clouthink.synergy.account;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.synergy.account.service", "in.clouthink.synergy.account.repository"})
@EnableMongoRepositories({"in.clouthink.synergy.account.repository"})
@EnableConfigurationProperties(AccountPasswordConfigurationProperties.class)
public class AccountServiceConfiguration {

}

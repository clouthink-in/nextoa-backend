package in.clouthink.synergy.team;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"in.clouthink.synergy.team.repository"})
public class TeamRepositoryConfiguration {

}

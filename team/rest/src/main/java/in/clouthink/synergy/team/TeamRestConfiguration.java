package in.clouthink.synergy.team;

import in.clouthink.synergy.team.engine.TeamEngineConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.synergy.team.rest.controller", "in.clouthink.synergy.team.rest.support.impl"})
@Import({TeamRepositoryConfiguration.class, TeamServiceConfiguration.class, TeamEngineConfiguration.class})
public class TeamRestConfiguration {

}

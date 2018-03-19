package in.clouthink.synergy.team;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.team.rest.controller", "in.clouthink.synergy.team.rest.support.mock"})
public class TeamMockConfiguration {

}

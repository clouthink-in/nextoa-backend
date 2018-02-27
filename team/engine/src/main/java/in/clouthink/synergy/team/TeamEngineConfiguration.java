package in.clouthink.synergy.team;

import in.clouthink.synergy.team.akka.AkkaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @auther dz
 */
@Configuration
@Import(AkkaConfiguration.class)
public class TeamEngineConfiguration {

}

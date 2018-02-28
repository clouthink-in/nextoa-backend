package in.clouthink.synergy.team.engine;

import in.clouthink.synergy.team.engine.support.AkkaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @auther dz
 */
@Configuration
@Import(AkkaConfiguration.class)
public class TeamEngineConfiguration {

}

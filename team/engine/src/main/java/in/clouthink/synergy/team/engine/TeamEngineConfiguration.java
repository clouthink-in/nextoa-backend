package in.clouthink.synergy.team.engine;

import in.clouthink.synergy.team.engine.support.AkkaConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @auther dz
 */
@Configuration
@ComponentScan({"in.clouthink.synergy.team.engine.business",
        "in.clouthink.synergy.team.engine.service",
        "in.clouthink.synergy.team.engine.actor"})
@Import(AkkaConfiguration.class)
public class TeamEngineConfiguration {

}

package in.clouthink.synergy.team;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.synergy.team.service"})
public class ServiceConfiguration {

	public static final String CONFIGURATION_PREFIX = "in.clouthink.synergy.team.service";

}

package in.clouthink.synergy.team.engine.support;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringExtension springExtension() {
        return new SpringExtension();
    }

    @Bean
    @Autowired
    public ActorSystem actorSystem(SpringExtension springExtension) {
        ActorSystem actorSystem = ActorSystem.create("synergy-actor-system", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

}

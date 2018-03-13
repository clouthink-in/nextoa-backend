package in.clouthink.synergy.team.engine.support;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AkkaConfiguration {

    @Bean
    @Autowired
    public SpringExtension springExtension(ApplicationContext applicationContext) {
        return new SpringExtension(applicationContext);
    }

    @Bean(destroyMethod = "shutdown")
    @DependsOn("springExtension")
    public ActorSystem actorSystem() {
        return ActorSystem.create("synergy-actor-system", akkaConfiguration());
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

}

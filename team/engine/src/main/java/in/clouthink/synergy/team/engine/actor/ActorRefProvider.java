package in.clouthink.synergy.team.engine.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import in.clouthink.synergy.team.engine.support.SpringExtension;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.support.Assert;

import javax.annotation.PreDestroy;

/**
 * @auther dz
 */
@Component
public class ActorRefProvider implements InitializingBean {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    private ActorRef activityActorRef;

    public ActorRef getActivityActorRef() {
        return activityActorRef;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(actorSystem);
        Assert.notNull(springExtension);

        //please make sure your 'activityRouter' is configured in application.conf
        activityActorRef = actorSystem.actorOf(springExtension.props("activityActor"), "activityRouter");
    }

    @PreDestroy
    public void onDestroy() {
        actorSystem.shutdown();
    }

}

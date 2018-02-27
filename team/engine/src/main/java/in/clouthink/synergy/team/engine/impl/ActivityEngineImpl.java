package in.clouthink.synergy.team.engine.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.actor.EndActivityRequest;
import in.clouthink.synergy.team.actor.ReadActivityResponse;
import in.clouthink.synergy.team.akka.SpringExtension;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.engine.ActivityEngine;
import in.clouthink.synergy.team.exception.EngineException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 */
@Service
public class ActivityEngineImpl implements ActivityEngine {

    private static final Log logger = LogFactory.getLog(ActivityEngineImpl.class);

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    @Override
    public void markActivityAsRead(String id, User user) {
        try {
            ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor"), "worker-actor");

            workerActor.tell(new EndActivityRequest(id, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(workerActor,
                                                    new ReadActivityResponse(),
                                                    Timeout.durationToTimeout(duration));

            logger.info("Response: " + Await.result(awaitable, duration));
        } catch (Exception e) {
            throw new EngineException(e);
        } finally {
            actorSystem.terminate();
            try {
                Await.result(actorSystem.whenTerminated(), Duration.Inf());
            } catch (Exception e1) {
                throw new EngineException(e1);
            }
        }
    }

    @Override
    public void startActivityAction(ActivityAction activityAction) {

    }

    @Override
    public void replyActivityAction(ActivityAction previousAction, ActivityAction activityAction) {

    }

    @Override
    public void forwardActivityAction(ActivityAction previousAction, ActivityAction activityAction) {

    }

    @Override
    public void revokeActivityAction(ActivityAction activityAction) {

    }

    @Override
    public void endActivityAction(ActivityAction activityAction) {

    }

    @Override
    public void terminateActivityAction(ActivityAction activityAction) {

    }
}

package in.clouthink.synergy.team.engine.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.engine.actor.*;
import in.clouthink.synergy.team.engine.service.TeamEngine;
import in.clouthink.synergy.team.engine.support.SpringExtension;
import in.clouthink.synergy.team.exception.EngineException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 */
@Service
public class TeamEngineImpl implements TeamEngine {

    private static final Log logger = LogFactory.getLog(TeamEngineImpl.class);

    //The recommend value is the count of CPU core
    @Value("${synergy.team.queue.count:4}")
    private int howManyQueues;

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    @Override
    public void markActivityAsRead(String id, User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new ReadActivityRequest(id, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
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
    public void startActivity(String id,
                              in.clouthink.synergy.team.domain.request.StartActivityRequest request,
                              User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new StartActivityRequest(id, request, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new StartActivityResponse(),
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
    public void replyActivity(String activityId, in.clouthink.synergy.team.domain.request.ReplyActivityRequest request,
                              User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new ReplyActivityRequest(activityId, request, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new ReplyActivityResponse(),
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
    public void forwardActivity(String id, in.clouthink.synergy.team.domain.request.ForwardActivityRequest request,
                                User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new ForwardActivityRequest(id, request, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new ForwardActivityResponse(),
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
    public void revokeActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new RevokeActivityRequest(id, reason, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new RevokeActivityResponse(),
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
    public void endActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new EndActivityRequest(id, reason, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new EndActivityResponse(),
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
    public void terminateActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new TerminateActivityRequest(id, reason, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new TerminateActivityResponse(),
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
    public void deleteActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorSystem.actorOf(springExtension.props("activityActor"), "worker-actor");

            actorRef.tell(new DeleteActivityRequest(id, reason, user), null);

            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
            Future<Object> awaitable = Patterns.ask(actorRef,
                                                    new DeleteActivityResponse(),
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

}

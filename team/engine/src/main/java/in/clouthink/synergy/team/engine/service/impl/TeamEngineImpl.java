package in.clouthink.synergy.team.engine.service.impl;

import akka.actor.ActorRef;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.engine.actor.*;
import in.clouthink.synergy.team.engine.service.TeamEngine;
import in.clouthink.synergy.team.exception.EngineException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 */
@Service
public class TeamEngineImpl implements TeamEngine {

    private static final Log logger = LogFactory.getLog(TeamEngineImpl.class);

    @Value("{$team.queue.response.timeout:1000}")
    private int responseTimeout;

    @Autowired
    private ActorRefProvider actorRefProvider;

    @Override
    public void markActivityAsRead(String id, User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            actorRef.tell(new ReadActivityRequest(id, user), ActorRef.noSender());
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<StartActivityResponse> startActivity(String id,
                                                                  in.clouthink.synergy.team.domain.request.StartActivityRequest request,
                                                                  User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);


            return PatternsCS.ask(actorRef,
                                  new StartActivityRequest(id, request, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((StartActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<ReplyActivityResponse> replyActivity(String activityId,
                                                                  in.clouthink.synergy.team.domain.request.ReplyActivityRequest request,
                                                                  User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new ReplyActivityRequest(activityId, request, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((ReplyActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<ForwardActivityResponse> forwardActivity(String id,
                                                                      in.clouthink.synergy.team.domain.request.ForwardActivityRequest request,
                                                                      User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new ForwardActivityRequest(id, request, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((ForwardActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<RevokeActivityResponse> revokeActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new RevokeActivityRequest(id, reason, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((RevokeActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<EndActivityResponse> endActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new EndActivityRequest(id, reason, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((EndActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<TerminateActivityResponse> terminateActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new TerminateActivityRequest(id, reason, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((TerminateActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

    @Override
    public CompletableFuture<DeleteActivityResponse> deleteActivity(String id, String reason, User user) {
        try {
            ActorRef actorRef = actorRefProvider.getActivityActorRef();

            FiniteDuration duration = FiniteDuration.create(responseTimeout, TimeUnit.MILLISECONDS);

            return PatternsCS.ask(actorRef,
                                  new DeleteActivityRequest(id, reason, user),
                                  Timeout.durationToTimeout(duration))
                             .toCompletableFuture()
                             .thenCompose(o -> CompletableFuture.completedFuture((DeleteActivityResponse) o));
        } catch (Throwable e) {
            throw new EngineException(e);
        }
    }

}

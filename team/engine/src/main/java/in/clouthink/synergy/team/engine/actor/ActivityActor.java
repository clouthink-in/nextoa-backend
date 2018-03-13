package in.clouthink.synergy.team.engine.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import in.clouthink.synergy.team.engine.business.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.PartialFunction;

/**
 * The akka actor to convert the concurrent invoking team business to queued request.
 *
 * @auther dz
 */
@Component("activityActor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActivityActor extends AbstractLoggingActor {

    /**
     * Don't invoke this in spring , dependence injection is not guarantee.
     *
     * @return
     */
    public static Props props() {
        return Props.create(ActivityActor.class, () -> new ActivityActor());
    }

    @Autowired
    private TeamService teamService;

    public PartialFunction receive() {
        return ReceiveBuilder
                .match(DeleteActivityRequest.class, this::deleteActivity)
                .match(EndActivityRequest.class, this::endActivity)
                .match(ReadActivityRequest.class, this::markActivityAsRead)
                .match(RevokeActivityRequest.class, this::revokeActivity)
                .match(TerminateActivityRequest.class, this::terminateActivity)
                .match(StartActivityRequest.class, this::startActivity)
                .match(ReplyActivityRequest.class, this::replyActivity)
                .match(ForwardActivityRequest.class, this::forwardActivity)
                .matchAny(o -> log().info("received unknown message: {}", o))
                .build();
    }

    void deleteActivity(DeleteActivityRequest request) {
        try {
            teamService.deleteActivity(request.getActivityId(), request.getReason(), request.getUser());
        } catch (Throwable e) {
            sender().tell(new DeleteActivityResponse(e), self());
        }
    }

    void endActivity(EndActivityRequest request) {
        try {
            teamService.endActivity(request.getActivityId(),
                                    request.getReason(),
                                    request.getUser());
        } catch (Throwable e) {
            sender().tell(new EndActivityResponse(e), self());
        }
    }

    void markActivityAsRead(ReadActivityRequest request) {
        try {
            teamService.markActivityAsRead(request.getActivityId(), request.getUser());
        } catch (Throwable e) {
            sender().tell(new ReadActivityResponse(e), self());
        }
    }

    void revokeActivity(RevokeActivityRequest request) {
        try {
            teamService.revokeActivity(request.getActivityId(),
                                       request.getReason(),
                                       request.getUser());
        } catch (Throwable e) {
            sender().tell(new RevokeActivityResponse(e), self());
        }
    }

    void terminateActivity(TerminateActivityRequest request) {
        try {
            teamService.terminateActivity(request.getActivityId(),
                                          request.getReason(),
                                          request.getUser());
        } catch (Throwable e) {
            sender().tell(new TerminateActivityResponse(e), self());
        }
    }

    void startActivity(StartActivityRequest request) {
        try {
            teamService.startActivity(request.getActivityId(),
                                      request.getRequest(),
                                      request.getUser());
        } catch (Throwable e) {
            sender().tell(new StartActivityResponse(e), self());
        }
    }

    void replyActivity(ReplyActivityRequest request) {
        try {
            teamService.replyActivity(request.getActivityId(),
                                      request.getRequest(),
                                      request.getUser());
        } catch (Throwable e) {
            sender().tell(new ReplyActivityResponse(e), self());
        }
    }

    void forwardActivity(ForwardActivityRequest request) {
        try {
            teamService.forwardActivity(request.getActivityId(),
                                        request.getRequest(),
                                        request.getUser());
        } catch (Throwable e) {
            sender().tell(new ForwardActivityResponse(e), self());
        }
    }

}

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
            sender().tell(new DeleteActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new DeleteActivityResponse(e), self());
            log().error(e, "#deleteActivity failure");
        }
    }

    void endActivity(EndActivityRequest request) {
        try {
            teamService.endActivity(request.getActivityId(),
                                    request.getReason(),
                                    request.getUser());
            sender().tell(new EndActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new EndActivityResponse(e), self());
            log().error(e, "#endActivity failure");
        }
    }

    void markActivityAsRead(ReadActivityRequest request) {
        try {
            teamService.markActivityAsRead(request.getActivityId(), request.getUser());
        } catch (Throwable e) {
            log().error(e, "#markActivityAsRead failure");
        }
    }

    void revokeActivity(RevokeActivityRequest request) {
        try {
            teamService.revokeActivity(request.getActivityId(),
                                       request.getReason(),
                                       request.getUser());
            sender().tell(new RevokeActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new RevokeActivityResponse(e), self());
            log().error(e, "#revokeActivity failure");
        }
    }

    void terminateActivity(TerminateActivityRequest request) {
        try {
            teamService.terminateActivity(request.getActivityId(),
                                          request.getReason(),
                                          request.getUser());
            sender().tell(new TerminateActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new TerminateActivityResponse(e), self());
            log().error(e, "#terminateActivity failure");
        }
    }

    void startActivity(StartActivityRequest request) {
        try {
            teamService.startActivity(request.getActivityId(),
                                      request.getRequest(),
                                      request.getUser());
            sender().tell(new StartActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new StartActivityResponse(e), self());
            log().error(e, "#startActivity failure");
        }
    }

    void replyActivity(ReplyActivityRequest request) {
        try {
            teamService.replyActivity(request.getActivityId(),
                                      request.getRequest(),
                                      request.getUser());
            sender().tell(new ReplyActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new ReplyActivityResponse(e), self());
            log().error(e, "#replyActivity failure");
        }
    }

    void forwardActivity(ForwardActivityRequest request) {
        try {
            teamService.forwardActivity(request.getActivityId(),
                                        request.getRequest(),
                                        request.getUser());
            sender().tell(new ForwardActivityResponse(null), self());
        } catch (Throwable e) {
            sender().tell(new ForwardActivityResponse(e), self());
            log().error(e, "#forwardActivity failure");
        }
    }

}

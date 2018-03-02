package in.clouthink.synergy.team.engine.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import in.clouthink.synergy.team.engine.business.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The akka actor to convert the concurrent invoking team business to queued request.
 *
 * @auther dz
 */
@Component("activityActor")
@Scope("prototype")
public class ActivityActor extends AbstractActor {

    @Autowired
    private TeamService teamService;

    public ActivityActor() {
        receive(ReceiveBuilder
                        .match(DeleteActivityRequest.class, this::deleteActivity)
                        .match(EndActivityRequest.class, this::endActivity)
                        .match(ReadActivityRequest.class, this::markActivityAsRead)
                        .match(RevokeActivityRequest.class, this::revokeActivity)
                        .match(TerminateActivityRequest.class, this::terminateActivity)
                        .match(StartActivityRequest.class, this::startActivity)
                        .match(ReplyActivityRequest.class, this::replyActivity)
                        .match(ForwardActivityRequest.class, this::forwardActivity)
                        .build());
    }


    void deleteActivity(DeleteActivityRequest request) {
        teamService.deleteActivity(request.getActivityId(), request.getReason(), request.getUser());
    }

    void endActivity(EndActivityRequest request) {
        teamService.endActivity(request.getActivityId(),
                                request.getReason(),
                                request.getUser());
    }

    void markActivityAsRead(ReadActivityRequest request) {
        teamService.markActivityAsRead(request.getActivityId(), request.getUser());
    }

    void revokeActivity(RevokeActivityRequest request) {
        teamService.revokeActivity(request.getActivityId(),
                                   request.getReason(),
                                   request.getUser());
    }

    void terminateActivity(TerminateActivityRequest request) {
        teamService.terminateActivity(request.getActivityId(),
                                      request.getReason(),
                                      request.getUser());
    }

    void startActivity(StartActivityRequest request) {
        teamService.startActivity(request.getActivityId(),
                                  request.getRequest(),
                                  request.getUser());
    }

    void replyActivity(ReplyActivityRequest request) {
        teamService.replyActivity(request.getActivityId(),
                                  request.getRequest(),
                                  request.getUser());
    }

    void forwardActivity(ForwardActivityRequest request) {
        teamService.forwardActivity(request.getActivityId(),
                                    request.getRequest(),
                                    request.getUser());
    }

}

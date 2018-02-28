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
                        .match(DeleteActivityRequest.class, request -> {
                            teamService.deleteActivity(request.getActivityId(), request.getReason(), request.getUser());
                        })
                        .match(EndActivityRequest.class, request -> {
                            teamService.endActivity(request.getActivityId(),
                                                    request.getReason(),
                                                    request.getUser());
                        })
                        .match(ReadActivityRequest.class, request -> {
                            teamService.markActivityAsRead(request.getActivityId(), request.getUser());
                        })
                        .match(RevokeActivityRequest.class, request -> {
                            teamService.revokeActivity(request.getActivityId(),
                                                       request.getReason(),
                                                       request.getUser());
                        })
                        .match(TerminateActivityRequest.class, request -> {
                            teamService.terminateActivity(request.getActivityId(),
                                                          request.getReason(),
                                                          request.getUser());
                        })
                        .match(StartActivityRequest.class, request -> {
                            teamService.startActivity(request.getActivityId(),
                                                      request.getRequest(),
                                                      request.getUser());
                        })
                        .match(ReplyActivityRequest.class, request -> {
                            teamService.replyActivity(request.getActivityId(),
                                                      request.getRequest(),
                                                      request.getUser());
                        })
                        .match(ForwardActivityRequest.class, request -> {
                            teamService.forwardActivity(request.getActivityId(),
                                                        request.getRequest(),
                                                        request.getUser());
                        })
                        .build());
    }

}

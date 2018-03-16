package in.clouthink.synergy.team.engine.business;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;
import in.clouthink.synergy.team.domain.request.SaveActivityRequest;
import in.clouthink.synergy.team.domain.request.StartActivityRequest;
import in.clouthink.synergy.team.engine.actor.UpdateActivityRequest;

/**
 * The service to define the team business which should be implemented in sync.
 * And be changed to async in actor system.
 * <p>
 * The service is only for inner invoke.
 *
 * @auther dz
 */
public interface TeamService {

    void markActivityAsRead(String id, User user);

    void updateActivity(String activityId, SaveActivityRequest request, User user);

    Activity copyActivity(String activityId, User user);

    void startActivity(String activityId, StartActivityRequest request, User user);

    void replyActivity(String activityId, ReplyActivityRequest request, User user);

    void forwardActivity(String id, ForwardActivityRequest request, User user);

    void revokeActivity(String activityId, String reason, User user);

    void endActivity(String activityId, String reason, User user);

    void terminateActivity(String activityId, String reason, User user);

    void deleteActivity(String activityId, String reason, User user);

}

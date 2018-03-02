package in.clouthink.synergy.team.engine.actor;

import akka.routing.ConsistentHashingRouter;
import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class ReplyActivityRequest implements Serializable, ConsistentHashingRouter.ConsistentHashable {

    private final String activityId;
    private final in.clouthink.synergy.team.domain.request.ReplyActivityRequest request;
    private final User user;

    public ReplyActivityRequest(String activityId,
                                in.clouthink.synergy.team.domain.request.ReplyActivityRequest request, User user) {
        this.activityId = activityId;
        this.request = request;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public in.clouthink.synergy.team.domain.request.ReplyActivityRequest getRequest() {
        return request;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Object consistentHashKey() {
        return this.activityId;
    }
}

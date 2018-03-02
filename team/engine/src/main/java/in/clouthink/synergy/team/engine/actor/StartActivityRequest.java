package in.clouthink.synergy.team.engine.actor;

import akka.routing.ConsistentHashingRouter;
import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class StartActivityRequest implements Serializable, ConsistentHashingRouter.ConsistentHashable {

    private final String activityId;
    private final in.clouthink.synergy.team.domain.request.StartActivityRequest request;
    private final User user;

    public StartActivityRequest(String activityId,
                                in.clouthink.synergy.team.domain.request.StartActivityRequest request, User user) {
        this.activityId = activityId;
        this.request = request;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public in.clouthink.synergy.team.domain.request.StartActivityRequest getRequest() {
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

package in.clouthink.synergy.team.engine.actor;

import akka.routing.ConsistentHashingRouter;
import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class CopyActivityRequest implements Serializable, ConsistentHashingRouter.ConsistentHashable {

    private final String activityId;
    private final User user;

    public CopyActivityRequest(String activityId, User user) {
        this.activityId = activityId;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Object consistentHashKey() {
        return this.activityId;
    }

}

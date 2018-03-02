package in.clouthink.synergy.team.engine.actor;

import akka.routing.ConsistentHashingRouter;
import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class RevokeActivityRequest implements Serializable, ConsistentHashingRouter.ConsistentHashable {
    private final String activityId;

    private final String reason;

    private final User user;

    public RevokeActivityRequest(String activityId, String reason, User user) {
        this.activityId = activityId;
        this.reason = reason;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getReason() {
        return reason;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Object consistentHashKey() {
        return this.activityId;
    }
}

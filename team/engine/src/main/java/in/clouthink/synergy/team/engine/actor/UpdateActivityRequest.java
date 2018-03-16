package in.clouthink.synergy.team.engine.actor;

import akka.routing.ConsistentHashingRouter;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.request.SaveActivityRequest;

import java.io.Serializable;

/**
 * @auther dz
 */
public class UpdateActivityRequest implements Serializable, ConsistentHashingRouter.ConsistentHashable {

    private final String activityId;
    private final SaveActivityRequest saveActivityRequest;
    private final User user;

    public UpdateActivityRequest(String activityId, SaveActivityRequest saveActivityRequest, User user) {
        this.activityId = activityId;
        this.saveActivityRequest = saveActivityRequest;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public SaveActivityRequest getRequest() {
        return saveActivityRequest;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Object consistentHashKey() {
        return this.activityId;
    }

}

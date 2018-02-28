package in.clouthink.synergy.team.engine.actor;

import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class ForwardActivityRequest implements Serializable {

    private final String activityId;
    private final in.clouthink.synergy.team.domain.request.ForwardActivityRequest request;
    private final User user;

    public ForwardActivityRequest(String activityId,
                                  in.clouthink.synergy.team.domain.request.ForwardActivityRequest request, User user) {
        this.activityId = activityId;
        this.request = request;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public in.clouthink.synergy.team.domain.request.ForwardActivityRequest getRequest() {
        return request;
    }

    public User getUser() {
        return user;
    }

}

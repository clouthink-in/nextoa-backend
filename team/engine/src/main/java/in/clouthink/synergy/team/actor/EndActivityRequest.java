package in.clouthink.synergy.team.actor;

import in.clouthink.synergy.account.domain.model.User;

/**
 * @auther dz
 */
public class EndActivityRequest {

    private final String activityId;

    private final User user;

    public EndActivityRequest(String activityId, User user) {
        this.activityId = activityId;
        this.user = user;
    }

    public String getActivityId() {
        return activityId;
    }

    public User getUser() {
        return user;
    }
}

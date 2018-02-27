package in.clouthink.synergy.team.event;

import in.clouthink.synergy.account.domain.model.User;

/**
 * @auther dz
 */
public class ForwardActivityEvent {

    private final String activityId;

    private final User user;

    public ForwardActivityEvent(String activityId, User user) {
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

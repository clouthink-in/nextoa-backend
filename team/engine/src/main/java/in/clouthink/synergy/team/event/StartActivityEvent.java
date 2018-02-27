package in.clouthink.synergy.team.event;

import in.clouthink.synergy.account.domain.model.User;

/**
 * @auther dz
 */
public class StartActivityEvent {

    private final String activityId;

    private final User user;

    public StartActivityEvent(String activityId, User user) {
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

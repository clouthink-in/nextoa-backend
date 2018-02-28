package in.clouthink.synergy.team.engine.actor;

import in.clouthink.synergy.account.domain.model.User;

import java.io.Serializable;

/**
 * @auther dz
 */
public class TerminateActivityRequest implements Serializable {

    private final String activityId;

    private final String reason;

    private final User user;

    public TerminateActivityRequest(String activityId, String reason, User user) {
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

}

package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.ActivityAction;

/**
 * @auther dz
 */
public interface BusinessService {

    void markActivityAsRead(String id, User user);

    void startActivityAction(ActivityAction activityAction);

    void replyActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void forwardActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void revokeActivityAction(ActivityAction activityAction);

    void endActivityAction(ActivityAction activityAction);

    void terminateActivityAction(ActivityAction activityAction);

}

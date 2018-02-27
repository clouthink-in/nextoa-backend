package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;

/**
 * 协作请求内部服务,只提供给内部流转的时候调用,RestSupport不能调用本服务中的方法
 *
 * @author dz
 */
public interface ActivityEngine {

    void markActivityAsRead(String id, User user);

    void startActivityAction(ActivityAction activityAction);

    void replyActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void forwardActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void revokeActivityAction(ActivityAction activityAction);

    void endActivityAction(ActivityAction activityAction);

    void terminateActivityAction(ActivityAction activityAction);

}

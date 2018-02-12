package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;

/**
 * 协作请求内部服务,只提供给内部流转的时候调用,RestSupport不能调用本服务中的方法
 */
public interface ActivityEngine {

    void markActivityAsRead(String id, User user);

    void markActivityAsRead(Activity activity, User user);

    void handleStartActivityAction(ActivityAction activityAction);

    void handleReplyActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void handleForwardActivityAction(ActivityAction previousAction, ActivityAction activityAction);

    void handleRevokeActivityAction(ActivityAction activityAction);

    void handleEndActivityAction(ActivityAction activityAction);

    void handleTerminateActivityAction(ActivityAction activityAction);
}

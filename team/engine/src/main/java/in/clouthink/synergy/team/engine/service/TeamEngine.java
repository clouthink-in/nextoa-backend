package in.clouthink.synergy.team.engine.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;
import in.clouthink.synergy.team.domain.request.StartActivityRequest;

/**
 * 协作请求内部服务,只提供给内部流转的时候调用,RestSupport不能调用本服务中的方法
 *
 * @author dz
 */
public interface TeamEngine {

    void markActivityAsRead(String activityId, User user);

    void startActivity(String activityId, StartActivityRequest request, User user);

    void replyActivity(String activityId, ReplyActivityRequest request, User user);

    void forwardActivity(String id, ForwardActivityRequest request, User user);

    void revokeActivity(String activityId, String reason, User user);

    void endActivity(String activityId, String reason, User user);

    void terminateActivity(String activityId, String reason, User user);

    void deleteActivity(String activityId, String reason, User user);

}

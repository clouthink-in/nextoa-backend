package in.clouthink.synergy.team.engine.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.request.ForwardActivityRequest;
import in.clouthink.synergy.team.domain.request.ReplyActivityRequest;
import in.clouthink.synergy.team.domain.request.StartActivityRequest;
import in.clouthink.synergy.team.engine.actor.*;

import java.util.concurrent.CompletableFuture;

/**
 * 协作请求内部服务,只提供给内部流转的时候调用,RestSupport不能调用本服务中的方法
 *
 * @author dz
 */
public interface TeamEngine {

    void markActivityAsRead(String activityId, User user);

    CompletableFuture<StartActivityResponse> startActivity(String activityId, StartActivityRequest request, User user);

    CompletableFuture<ReplyActivityResponse> replyActivity(String activityId, ReplyActivityRequest request, User user);

    CompletableFuture<ForwardActivityResponse> forwardActivity(String id, ForwardActivityRequest request, User user);

    CompletableFuture<RevokeActivityResponse> revokeActivity(String activityId, String reason, User user);

    CompletableFuture<EndActivityResponse> endActivity(String activityId, String reason, User user);

    CompletableFuture<TerminateActivityResponse> terminateActivity(String activityId, String reason, User user);

    CompletableFuture<DeleteActivityResponse> deleteActivity(String activityId, String reason, User user);

}

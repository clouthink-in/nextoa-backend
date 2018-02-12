package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 */
public interface ActivityService {

    Page<Activity> listActivities(ActivityQueryRequest queryRequest);

    Page<Activity> listActivities(ActivityQueryRequest queryRequest,
                                  ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                  User user);

    long countOfActivities(ActivityQueryRequest queryRequest,
                           ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                           User user);

    /**
     * 可查看所有activity,无权限控制,仅用于在list中需要转化activity的summary的时候使用
     *
     * @param id
     * @return
     */
    Activity findActivityById(String id);

    /**
     * 只能查看user拥有的activity
     *
     * @param id
     * @param user
     * @return
     */
    Activity findActivityById(String id, User user);

    Page<ActivityAction> getActivityActionHistory(String id, ActivityActionQueryRequest queryRequest);

    /**
     * Please seee <code>ActivityService#getActivityProcessHistoryList</code>
     *
     * @param id
     * @param queryRequest
     * @return
     */
    List<ActivityAction> getActivityActionHistoryList(String id, ActivityActionQueryRequest queryRequest);

    /**
     * 处理意见历史
     *
     * @param id
     * @return
     */
    List<ActivityAction> getActivityProcessHistoryList(String id, User user);

    Activity createActivity(SaveActivityRequest request, User user);

    Activity copyActivity(String id, User user);

    void updateActivity(String id, SaveActivityRequest request, User user);

    void deleteActivity(String id, User user);

    void revokeActivity(String id, User user);

    void startActivity(String id, StartActivityRequest request, User user);

    void printActivity(String id, User user);

    void replyActivity(String id, ReplyActivityRequest request, User user);

    void forwardActivity(String id, ForwardActivityRequest request, User user);

    void endActivity(String id, User user);

    void endActivity(Activity activity, User user);

    void terminateActivity(String id, User user);

    void terminateActivity(Activity activity, User user);

    ActivityAction findActivityActionById(String id);

    boolean isRead(Activity activity, User user);

    boolean isFavorite(Activity activity, User user);

    void deleteActivityAttachment(String activityId, String fileId, User user);

    void markActivityBusinessComplete(Activity activity);

}

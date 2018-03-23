package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.ActivityActionSearchRequest;
import in.clouthink.synergy.team.domain.request.ActivitySearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The activity  service only including query api.
 *
 * @auther dz
 */
public interface ActivityQueryService {


    /**
     * @param queryRequest
     * @return
     */
    Page<Activity> listActivities(ActivitySearchRequest queryRequest);

    /**
     * @param queryRequest
     * @param includeOrExcludeStatus
     * @param user
     * @return
     */
    Page<Activity> listActivities(ActivitySearchRequest queryRequest,
                                  ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                  User user);

    /**
     * @param queryRequest
     * @param includeOrExcludeStatus
     * @param user
     * @return
     */
    long countOfActivities(ActivitySearchRequest queryRequest,
                           ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
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

    /**
     * @param id
     * @param queryRequest
     * @return
     */
    Page<ActivityAction> getActivityActionHistory(String id, ActivityActionSearchRequest queryRequest);

    /**
     * Please seee <code>ActivityService#getActivityProcessHistoryList</code>
     *
     * @param id
     * @param queryRequest
     * @return
     */
    List<ActivityAction> getActivityActionHistoryList(String id, ActivityActionSearchRequest queryRequest);

    /**
     * 处理意见历史
     *
     * @param id
     * @return
     */
    List<ActivityAction> getActivityProcessHistoryList(String id, User user);

    /**
     *
     * @param id
     * @return
     */
    ActivityAction findActivityActionById(String id);

    /**
     * @param activity
     * @param user
     * @return
     */
    boolean isRead(Activity activity, User user);

    /**
     * @param activity
     * @param user
     * @return
     */
    boolean isFavorite(Activity activity, User user);

}

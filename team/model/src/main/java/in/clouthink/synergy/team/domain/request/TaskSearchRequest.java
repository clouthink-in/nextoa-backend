package in.clouthink.synergy.team.domain.request;


import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import in.clouthink.synergy.team.domain.model.TaskStatus;

/**
 * 任务查询参数
 */
public interface TaskSearchRequest extends DateRangedSearchRequest {

    enum IncludeOrExcludeStatus {
        INCLUDE, EXCLUDE
    }

    /**
     * @return 任务类型
     */
    String getCategory();

    /**
     * @return 协作请求标题
     */
    String getTitle();

    /**
     * @return 协作请求发起人
     */
    String getInitiatorUsername();

    /**
     * @return 任务状态
     */
    TaskStatus getTaskStatus();

}

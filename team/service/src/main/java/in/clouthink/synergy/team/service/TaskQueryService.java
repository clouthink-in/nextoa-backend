package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskSearchRequest;
import org.springframework.data.domain.Page;

/**
 * The task service including query api
 *
 * @author dz
 */
public interface TaskQueryService {

    /**
     * @param creatorName
     * @param queryParameter
     * @param user
     * @return
     */
    Page<Task> listTasksByActivityCreator(String creatorName, PageSearchRequest queryParameter, User user);

    /**
     * @param receiverName
     * @param queryParameter
     * @param user
     * @return
     */
    Page<Task> listTasksByReceiver(String receiverName, PageSearchRequest queryParameter, User user);

    /**
     * @param id
     * @return
     */
    Task findTaskById(String id);

    /**
     * 根据id查询任务, 通过user判断是否有权限
     *
     * @param id
     * @param user
     * @return
     */
    Task findTaskById(String id, User user);

    /**
     * @param queryParameter
     * @param includeOrExcludeStatus
     * @param user
     * @return
     */
    Page<Task> listTasks(TaskSearchRequest queryParameter,
                         TaskSearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                         User user);

    /**
     * @param bizRefId
     * @param queryParameter
     * @return
     */
    Page<Task> listActiveTasks(String bizRefId, PageSearchRequest queryParameter);

    /**
     * @param queryParameter
     * @param user
     * @return
     */
    Page<Task> listFavoriteTasks(TaskSearchRequest queryParameter, User user);

    /**
     * @param queryParameter
     * @param includeOrExcludeStatus
     * @param user
     * @return
     */
    long countOfTasks(TaskSearchRequest queryParameter,
                      TaskSearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                      User user);

    /**
     * @param queryParameter
     * @param user
     * @return
     */
    long countOfFavoriteTasks(PageSearchRequest queryParameter, User user);

    /**
     * @param task
     * @param user
     * @return
     */
    boolean isFavorite(Task task, User user);

}

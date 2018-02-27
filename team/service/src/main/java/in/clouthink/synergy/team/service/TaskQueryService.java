package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.domain.model.FavoriteTask;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
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
    Page<Task> listTasksByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user);

    /**
     * @param receiverName
     * @param queryParameter
     * @param user
     * @return
     */
    Page<Task> listTasksByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

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
    Page<Task> listTasks(TaskQueryRequest queryParameter,
                         TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                         User user);

    /**
     * @param bizRefId
     * @param queryParameter
     * @return
     */
    Page<Task> listActiveTasks(String bizRefId, PageQueryRequest queryParameter);

    /**
     * @param queryParameter
     * @param user
     * @return
     */
    Page<Task> listFavoriteTasks(TaskQueryRequest queryParameter, User user);

    /**
     * @param queryParameter
     * @param includeOrExcludeStatus
     * @param user
     * @return
     */
    long countOfTasks(TaskQueryRequest queryParameter,
                      TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                      User user);

    /**
     * @param queryParameter
     * @param user
     * @return
     */
    long countOfFavoriteTasks(PageQueryRequest queryParameter, User user);

    /**
     * @param task
     * @param user
     * @return
     */
    boolean isFavorite(Task task, User user);

}

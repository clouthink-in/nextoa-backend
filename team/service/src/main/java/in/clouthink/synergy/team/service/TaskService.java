package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.domain.model.FavoriteTask;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import org.springframework.data.domain.Page;

/**
 */
public interface TaskService {

    Page<Task> listTasksByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user);

    Page<Task> listTasksByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

    Task findTaskById(String id);

    /**
     * 根据id查询任务, 通过user判断是否有权限
     *
     * @param id
     * @param user
     * @return
     */
    Task findTaskById(String id, User user);

    Page<Task> listTasks(TaskQueryRequest queryParameter,
                         TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                         User user);

    Page<Task> listActiveTasks(String bizRefId, PageQueryRequest queryParameter);

    Page<Task> listFavoriteTasks(TaskQueryRequest queryParameter, User user);

    long countOfTasks(TaskQueryRequest queryParameter,
                      TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                      User user);

    long countOfFavoriteTasks(PageQueryRequest queryParameter, User user);

    FavoriteTask addTaskToFavorite(String id, User user);

    void removeTaskFromFavorite(String id, User user);

    boolean isFavorite(Task task, User user);

}

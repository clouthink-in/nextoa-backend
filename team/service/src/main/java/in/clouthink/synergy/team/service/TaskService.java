package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.domain.model.FavoriteTask;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import org.springframework.data.domain.Page;

/**
 * task service
 *
 * @author dz
 */
public interface TaskService extends TaskQueryService {

    /**
     * @param id
     * @param user
     * @return
     */
    FavoriteTask addTaskToFavorite(String id, User user);

    /**
     * @param id
     * @param user
     */
    void removeTaskFromFavorite(String id, User user);

}

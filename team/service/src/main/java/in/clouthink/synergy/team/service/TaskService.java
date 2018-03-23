package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.FavoriteTask;

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

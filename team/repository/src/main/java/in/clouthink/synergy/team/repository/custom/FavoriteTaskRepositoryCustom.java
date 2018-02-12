package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.account.domain.model.User;
import org.springframework.data.domain.Page;

import in.clouthink.synergy.team.domain.model.FavoriteTask;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;

public interface FavoriteTaskRepositoryCustom {
    
    Page<FavoriteTask> queryPage(User createdBy,
                                 TaskQueryRequest queryRequest);

}

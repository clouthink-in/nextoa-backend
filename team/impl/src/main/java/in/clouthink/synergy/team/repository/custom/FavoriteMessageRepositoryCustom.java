package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.account.domain.model.User;
import org.springframework.data.domain.Page;

import in.clouthink.synergy.team.domain.model.FavoriteMessage;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;

public interface FavoriteMessageRepositoryCustom {
    
    Page<FavoriteMessage> queryPage(User createdBy,
                                    TaskQueryRequest queryRequest);

}

package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.FavoriteMessage;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.repository.custom.FavoriteMessageRepositoryCustom;

/**
 *
 */
public interface FavoriteMessageRepository extends AbstractRepository<FavoriteMessage>, FavoriteMessageRepositoryCustom {

    FavoriteMessage findByMessageAndCreatedBy(Task task, User user);

    // Page<FavoriteMessage> findByCreatedBy(User user, Pageable pageable);

    long countByCreatedBy(User user);

}

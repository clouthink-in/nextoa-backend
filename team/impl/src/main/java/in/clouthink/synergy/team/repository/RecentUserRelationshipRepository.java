package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.RecentUserRelationship;

import java.util.List;

public interface RecentUserRelationshipRepository extends AbstractRepository<RecentUserRelationship> {

    List<RecentUserRelationship> findListByUserOrderByCreatedAtDesc(User user);

    void deleteByUser(User user);

}

package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.model.UserGroupRelationship;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface UserGroupRelationshipRepository extends AbstractRepository<UserGroupRelationship> {

    Page<UserGroupRelationship> findByGroup(Group group, Pageable pageable);

    Page<UserGroupRelationship> findByUser(User user, Pageable pageable);

    List<UserGroupRelationship> findListByUser(User user);

    UserGroupRelationship findByUserAndGroup(User user, Group group);

    UserGroupRelationship findFirstByGroup(Group group);

    long countByGroup(Group group);

}

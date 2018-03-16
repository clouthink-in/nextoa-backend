package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.repository.custom.UserRepositoryCustom;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {

    List<User> findByUsernameLike(String username);

    User findByUsername(String username);

    User findByTelephone(String telephone);

    User findByEmail(String email);

    Page<User> findByGroup(Group group, Pageable pageable);

    long countByGroup(Group group);

}
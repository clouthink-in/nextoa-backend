package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.repository.custom.UserRepositoryCustom;
import in.clouthink.synergy.shared.repository.AbstractRepository;

/**
 * @author dz
 */
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {

	User findByUsername(String username);

	User findByCellphone(String cellphone);

	User findByEmail(String email);

}
package in.clouthink.nextoa.account.repository;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.account.repository.custom.UserRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

/**
 * @author dz
 */
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {

	User findByUsername(String username);

	User findByCellphone(String cellphone);

	User findByEmail(String email);

}
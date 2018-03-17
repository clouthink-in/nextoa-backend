package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */

public interface RoleRepository extends AbstractRepository<Role> {

    List<Role> findListByType(RoleType type);

    Role findByCode(String code);

    Role findByName(String name);

}
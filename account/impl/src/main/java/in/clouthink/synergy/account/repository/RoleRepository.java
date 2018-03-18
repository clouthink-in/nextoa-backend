package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.repository.custom.RoleRepositoryCustom;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface RoleRepository extends AbstractRepository<Role>, RoleRepositoryCustom {

    Page<Role> findListByType(RoleType type, Pageable pageable);

    Role findFirstByCode(String code);

    Role findFirstByName(String name);

}
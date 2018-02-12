package in.clouthink.synergy.account.repository;

import in.clouthink.synergy.account.domain.model.AppRole;
import in.clouthink.synergy.shared.repository.AbstractRepository;

/**
 * @author dz
 */

public interface AppRoleRepository extends AbstractRepository<AppRole> {

	AppRole findByCode(String code);

	AppRole findByName(String name);

}
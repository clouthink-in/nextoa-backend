package in.clouthink.nextoa.account.repository;

import in.clouthink.nextoa.account.domain.model.AppRole;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

/**
 * @author dz
 */

public interface AppRoleRepository extends AbstractRepository<AppRole> {

	AppRole findByCode(String code);

	AppRole findByName(String name);

}
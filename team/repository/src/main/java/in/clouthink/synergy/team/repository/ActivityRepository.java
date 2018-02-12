package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.repository.custom.ActivityRepositoryCustom;

/**
 *
 */
public interface ActivityRepository extends AbstractRepository<Activity>, ActivityRepositoryCustom {

}
package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityTransition;

import java.util.List;


/**
 *
 */
public interface ActivityTransitionRepository extends AbstractRepository<ActivityTransition> {

    List<ActivityTransition> findListByActivityOrderByCreatedAtDesc(Activity activity);

}
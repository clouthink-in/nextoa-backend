package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.model.ActivityActionType;
import in.clouthink.synergy.team.repository.custom.ActivityActionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 *
 */
public interface ActivityActionRepository extends AbstractRepository<ActivityAction>, ActivityActionRepositoryCustom {

    List<ActivityAction> findListByActivity(Activity activity);

    List<ActivityAction> findListByIdInOrderByCreatedAtDesc(String[] ids);

    Page<ActivityAction> findPageByActivity(Activity activity, Pageable pageable);

    ActivityAction findFirstByActivityAndTypeAndCreatedBy(Activity activity, ActivityActionType type, User byWho);

    int countByActivityAndType(Activity activity, ActivityActionType type);

}
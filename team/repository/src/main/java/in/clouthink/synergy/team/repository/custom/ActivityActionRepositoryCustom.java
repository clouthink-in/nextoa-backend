package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.ActivityActionQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ActivityActionRepositoryCustom {

	Page<ActivityAction> queryPage(Activity activity, ActivityActionQueryRequest request);

	List<ActivityAction> queryList(Activity activity, ActivityActionQueryRequest request);

}

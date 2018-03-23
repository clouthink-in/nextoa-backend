package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.ActivityActionSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ActivityActionRepositoryCustom {

	Page<ActivityAction> queryPage(Activity activity, ActivityActionSearchRequest request);

	List<ActivityAction> queryList(Activity activity, ActivityActionSearchRequest request);

}

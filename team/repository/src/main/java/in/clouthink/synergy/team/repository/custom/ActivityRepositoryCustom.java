package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.request.ActivitySearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ActivityRepositoryCustom {

	Page<Activity> queryPage(User creator,
							 ActivitySearchRequest request,
							 ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	long queryCount(User creator,
					ActivitySearchRequest request,
					ActivitySearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	void updateReadCounter(String id, int readCounter);

	void markActivityBusinessComplete(Activity activity);
}

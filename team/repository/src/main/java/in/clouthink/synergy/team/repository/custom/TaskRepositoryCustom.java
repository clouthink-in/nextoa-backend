package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dz
 */
public interface TaskRepositoryCustom {

	Page<Task> queryPage(User receiver,
						 TaskSearchRequest queryRequest,
						 TaskSearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	Page<Task> queryPageByActivityCreator(String username, User byWho, Pageable pageable);

	Page<Task> queryPageByReceiver(String username, User byWho, Pageable pageable);

	long queryCount(User receiver,
					TaskSearchRequest queryRequest,
					TaskSearchRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	void markAsRead(String id);

}

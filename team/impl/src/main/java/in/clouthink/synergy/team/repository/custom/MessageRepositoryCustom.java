package in.clouthink.synergy.team.repository.custom;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dz
 */
public interface MessageRepositoryCustom {

	Page<Task> queryPage(User receiver,
						 TaskQueryRequest queryRequest,
						 TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	Page<Task> queryPageByActivityCreator(String username, User byWho, Pageable pageable);

	Page<Task> queryPageByReceiver(String username, User byWho, Pageable pageable);

	long queryCount(User receiver,
					TaskQueryRequest queryRequest,
					TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	void markAsRead(String id);

}

package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.repository.custom.TaskRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface TaskRepository extends AbstractRepository<Task>, TaskRepositoryCustom {

	@Deprecated
	Page<Task> findByTitleLikeAndReceiver(String title, User receiver, Pageable pageable);

	List<Task> findByBizRefId(String bizRefId);

	Task findByBizRefIdAndReceiver(String bizRefId, User user);

	Page<Task> findPageByBizRefId(String bizRefId, Pageable pageable);

}
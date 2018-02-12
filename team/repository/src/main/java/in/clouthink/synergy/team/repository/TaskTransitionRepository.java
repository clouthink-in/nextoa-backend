package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.TaskTransition;

import java.util.List;

/**
 *
 */
public interface TaskTransitionRepository extends AbstractRepository<TaskTransition> {

    List<TaskTransition> findByTask(Task task);

}
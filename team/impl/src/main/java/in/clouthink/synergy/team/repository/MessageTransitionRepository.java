package in.clouthink.synergy.team.repository;

import in.clouthink.synergy.shared.repository.AbstractRepository;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.TaskTransition;

import java.util.List;

/**
 *
 */
public interface MessageTransitionRepository extends AbstractRepository<TaskTransition> {

    List<TaskTransition> findByMessage(Task task);

}
package in.clouthink.synergy.team.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.TaskSearchParam;
import in.clouthink.synergy.team.rest.view.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface TaskRestSupport {

	Page<TaskView> listTasksByTitle(String title, PageSearchRequest queryParameter, User user);

	Page<TaskView> listTasksByActivityCreator(String creatorName, PageSearchRequest queryParameter, User user);

	Page<TaskView> listTasksByReceiver(String receiverName, PageSearchRequest queryParameter, User user);

	Page<TaskView> listAllTasks(TaskSearchParam queryRequest, User user);

	Page<TaskView> listPendingTasks(TaskSearchParam queryRequest, User user);

	Page<TaskView> listProcessedTasks(TaskSearchParam queryRequest, User user);

	Page<TaskView> listNotEndTasks(TaskSearchParam queryRequest, User user);

	Page<TaskView> listEndedTasks(TaskSearchParam queryRequest, User user);

	Page<TaskView> listFavoriteTasks(TaskSearchParam queryRequest, User user);

	TaskDetailView getTaskDetail(String id, User user);

	TaskParticipantView getTaskParticipant(String id, User user);

	void addTaskToFavorite(String id, User user);

	void removeTaskFromFavorite(String id, User user);

	long getCountOfAllTasks(TaskSearchParam queryRequest, User user);

	long getCountOfPendingTasks(TaskSearchParam queryRequest, User user);

	long getCountOfProcessedTasks(TaskSearchParam queryRequest, User user);

	long getCountOfEndedTasks(TaskSearchParam queryRequest, User user);

	long getCountOfNotEndTasks(TaskSearchParam queryRequest, User user);

	long getCountOfFavoriteTasks(PageSearchParam queryRequest, User user);

}

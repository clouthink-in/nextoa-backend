package in.clouthink.synergy.team.openapi.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.dto.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface TaskRestSupport {

	Page<TaskSummary> listTasksByTitle(String title, PageQueryRequest queryParameter, User user);

	Page<TaskSummary> listTasksByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user);

	Page<TaskSummary> listTasksByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

	Page<TaskSummary> listAllTasks(TaskQueryParameter queryRequest, User user);

	Page<TaskSummary> listPendingTasks(TaskQueryParameter queryRequest, User user);

	Page<TaskSummary> listProcessedTasks(TaskQueryParameter queryRequest, User user);

	Page<TaskSummary> listNotEndTasks(TaskQueryParameter queryRequest, User user);

	Page<TaskSummary> listEndedTasks(TaskQueryParameter queryRequest, User user);

	Page<TaskSummary> listFavoriteTasks(TaskQueryParameter queryRequest, User user);

	TaskDetail getTaskDetail(String id, User user);

	TaskParticipant getTaskParticipant(String id, User user);

	void addTaskToFavorite(String id, User user);

	void removeTaskFromFavorite(String id, User user);

	long getCountOfAllTasks(TaskQueryParameter queryRequest, User user);

	long getCountOfPendingTasks(TaskQueryParameter queryRequest, User user);

	long getCountOfProcessedTasks(TaskQueryParameter queryRequest, User user);

	long getCountOfEndedTasks(TaskQueryParameter queryRequest, User user);

	long getCountOfNotEndTasks(TaskQueryParameter queryRequest, User user);

	long getCountOfFavoriteTasks(PageQueryParameter queryRequest, User user);

}

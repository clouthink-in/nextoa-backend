package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.rest.dto.TaskDetail;
import in.clouthink.synergy.team.rest.dto.TaskParticipant;
import in.clouthink.synergy.team.rest.dto.TaskQueryParameter;
import in.clouthink.synergy.team.rest.dto.TaskSummary;
import in.clouthink.synergy.team.rest.support.TaskRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class TaskRestSupportMocker implements TaskRestSupport {

	@Override
	public Page<TaskSummary> listTasksByTitle(String title, PageQueryRequest queryParameter, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listTasksByActivityCreator(String creatorName, PageQueryRequest queryParameter,
														User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listTasksByReceiver(String receiverName, PageQueryRequest queryParameter, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listAllTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listPendingTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listProcessedTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listNotEndTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listEndedTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskSummary> listFavoriteTasks(TaskQueryParameter queryRequest, User user) {
		return null;
	}

	@Override
	public TaskDetail getTaskDetail(String id, User user) {
		return null;
	}

	@Override
	public TaskParticipant getTaskParticipant(String id, User user) {
		return null;
	}

	@Override
	public void addTaskToFavorite(String id, User user) {

	}

	@Override
	public void removeTaskFromFavorite(String id, User user) {

	}

	@Override
	public long getCountOfAllTasks(TaskQueryParameter queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfPendingTasks(TaskQueryParameter queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfProcessedTasks(TaskQueryParameter queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfEndedTasks(TaskQueryParameter queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfNotEndTasks(TaskQueryParameter queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfFavoriteTasks(PageQueryParameter queryRequest, User user) {
		return 0;
	}
}

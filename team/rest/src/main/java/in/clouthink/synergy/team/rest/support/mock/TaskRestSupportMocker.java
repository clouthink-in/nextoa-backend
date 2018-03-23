package in.clouthink.synergy.team.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.view.TaskDetailView;
import in.clouthink.synergy.team.rest.view.TaskParticipantView;
import in.clouthink.synergy.team.rest.param.TaskSearchParam;
import in.clouthink.synergy.team.rest.view.TaskView;
import in.clouthink.synergy.team.rest.support.TaskRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class TaskRestSupportMocker implements TaskRestSupport {

	@Override
	public Page<TaskView> listTasksByTitle(String title, PageSearchRequest queryParameter, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listTasksByActivityCreator(String creatorName, PageSearchRequest queryParameter,
													 User user) {
		return null;
	}

	@Override
	public Page<TaskView> listTasksByReceiver(String receiverName, PageSearchRequest queryParameter, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listAllTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listPendingTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listProcessedTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listNotEndTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listEndedTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public Page<TaskView> listFavoriteTasks(TaskSearchParam queryRequest, User user) {
		return null;
	}

	@Override
	public TaskDetailView getTaskDetail(String id, User user) {
		return null;
	}

	@Override
	public TaskParticipantView getTaskParticipant(String id, User user) {
		return null;
	}

	@Override
	public void addTaskToFavorite(String id, User user) {

	}

	@Override
	public void removeTaskFromFavorite(String id, User user) {

	}

	@Override
	public long getCountOfAllTasks(TaskSearchParam queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfPendingTasks(TaskSearchParam queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfProcessedTasks(TaskSearchParam queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfEndedTasks(TaskSearchParam queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfNotEndTasks(TaskSearchParam queryRequest, User user) {
		return 0;
	}

	@Override
	public long getCountOfFavoriteTasks(PageSearchParam queryRequest, User user) {
		return 0;
	}
}

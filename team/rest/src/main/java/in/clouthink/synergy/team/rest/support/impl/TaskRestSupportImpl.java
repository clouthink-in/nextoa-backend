package in.clouthink.synergy.team.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.TaskRestSupport;
import in.clouthink.synergy.team.service.TaskService;
import in.clouthink.synergy.team.service.ActivityService;
import in.clouthink.synergy.team.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Component
public class TaskRestSupportImpl implements TaskRestSupport {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ActivityService activityService;

	@Override
	public Page<TaskSummary> listTasksByTitle(String title, PageQueryRequest queryRequest, User user) {
		TaskQueryParameter messageQueryParameter = new TaskQueryParameter();
		messageQueryParameter.setTitle(title);
		messageQueryParameter.setStart(queryRequest.getStart());
		messageQueryParameter.setLimit(queryRequest.getLimit());

		Page<Task> messagePage = taskService.listTasks(messageQueryParameter, null, user);

		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listTasksByActivityCreator(String creatorName,
														   PageQueryRequest queryRequest,
														   User user) {
		Page<Task> messagePage = taskService.listTasksByActivityCreator(creatorName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listTasksByReceiver(String receiverName, PageQueryRequest queryRequest, User user) {
		Page<Task> messagePage = taskService.listTasksByReceiver(receiverName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listAllTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(null);
		Page<Task> messagePage = taskService.listTasks(queryRequest,
													   TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
													   user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listPendingTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PENDING);
		Page<Task> messagePage = taskService.listTasks(queryRequest,
													   TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
													   user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listProcessedTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PROCESSED);
		Page<Task> messagePage = taskService.listTasks(queryRequest,
													   TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
													   user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listNotEndTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		Page<Task> messagePage = taskService.listTasks(queryRequest,
													   TaskQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
													   user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listEndedTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		Page<Task> messagePage = taskService.listTasks(queryRequest,
													   TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
													   user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToTaskSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<TaskSummary> listFavoriteTasks(TaskQueryParameter queryRequest, User user) {
		Page<Task> messagePage = taskService.listFavoriteTasks(queryRequest, user);
		return new PageImpl<>(messagePage.getContent().stream().map(message -> {
			Activity activity = activityService.findActivityById(message.getBizRefId());
			TaskSummary result = TaskSummary.from(message, activity);
			result.setRead(activityService.isRead(activity, user));
			result.setFavorite(true);
			return result;
		}).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public TaskDetail getTaskDetail(String id, User user) {
		Task task = taskService.findTaskById(id, user);
		if (task == null) {
			return null;
		}
		Activity activity = activityService.findActivityById(task.getBizRefId(), user);
		TaskDetail result = TaskDetail.from(task, activity);
		result.setRead(activityService.isRead(activity, user));
		result.setFavorite(taskService.isFavorite(task, user));
		return result;
	}

	@Override
	public TaskParticipant getTaskParticipant(String id, User user) {
		Task task = taskService.findTaskById(id, user);
		if (task == null) {
			return null;
		}
		String actionRefId = task.getActionRefId();
		if (StringUtils.isEmpty(actionRefId)) {
			return null;
		}
		ActivityAction activityAction = activityService.findActivityActionById(actionRefId);
		if (activityAction == null) {
			return null;
		}

		return TaskParticipant.from(task.getSender(), user, activityAction);
	}

	@Override
	public long getCountOfAllTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(null);
		return taskService.countOfTasks(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfPendingTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PENDING);
		return taskService.countOfTasks(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfProcessedTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PROCESSED);
		return taskService.countOfTasks(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfEndedTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		return taskService.countOfTasks(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfNotEndTasks(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		return taskService.countOfTasks(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.EXCLUDE, user);
	}

	@Override
	public long getCountOfFavoriteTasks(PageQueryParameter queryRequest, User user) {
		return taskService.countOfFavoriteTasks(queryRequest, user);
	}

	@Override
	public void addTaskToFavorite(String id, User user) {
		taskService.addTaskToFavorite(id, user);
	}

	@Override
	public void removeTaskFromFavorite(String id, User user) {
		taskService.removeTaskFromFavorite(id, user);
	}


	private TaskSummary convertToTaskSummary(User user, Task task) {
		Activity activity = activityService.findActivityById(task.getBizRefId(), user);
		TaskSummary result = TaskSummary.from(task, activity);
		result.setFavorite(taskService.isFavorite(task, user));
		return result;
	}

}

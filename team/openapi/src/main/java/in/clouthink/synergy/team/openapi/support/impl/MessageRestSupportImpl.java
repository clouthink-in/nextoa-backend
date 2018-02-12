package in.clouthink.synergy.team.openapi.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.openapi.dto.*;
import in.clouthink.synergy.team.openapi.support.MessageRestSupport;
import in.clouthink.synergy.team.service.MessageService;
import in.clouthink.synergy.team.service.ActivityService;
import in.clouthink.synergy.team.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 *
 */
@Service
public class MessageRestSupportImpl implements MessageRestSupport {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActivityService activityService;

	@Override
	public Page<MessageSummary> listMessagesByTitle(String title, PageQueryRequest queryRequest, User user) {
		TaskQueryParameter messageQueryParameter = new TaskQueryParameter();
		messageQueryParameter.setTitle(title);
		messageQueryParameter.setStart(queryRequest.getStart());
		messageQueryParameter.setLimit(queryRequest.getLimit());

		Page<Task> messagePage = messageService.listMessages(messageQueryParameter, null, user);

		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listMessagesByActivityCreator(String creatorName,
														   PageQueryRequest queryRequest,
														   User user) {
		Page<Task> messagePage = messageService.listMessagesByActivityCreator(creatorName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listMessagesByReceiver(String receiverName, PageQueryRequest queryRequest, User user) {
		Page<Task> messagePage = messageService.listMessagesByReceiver(receiverName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listAllMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(null);
		Page<Task> messagePage = messageService.listMessages(queryRequest,
															 TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
															 user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listPendingMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PENDING);
		Page<Task> messagePage = messageService.listMessages(queryRequest,
															 TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
															 user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listProcessedMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PROCESSED);
		Page<Task> messagePage = messageService.listMessages(queryRequest,
															 TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
															 user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listNotEndMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		Page<Task> messagePage = messageService.listMessages(queryRequest,
															 TaskQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
															 user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listEndedMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		Page<Task> messagePage = messageService.listMessages(queryRequest,
															 TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE,
															 user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listFavoriteMessages(TaskQueryParameter queryRequest, User user) {
		Page<Task> messagePage = messageService.listFavoriteMessages(queryRequest, user);
		return new PageImpl<>(messagePage.getContent().stream().map(message -> {
			Activity activity = activityService.findActivityById(message.getBizRefId());
			MessageSummary result = MessageSummary.from(message, activity);
			result.setRead(activityService.isRead(activity, user));
			result.setFavorite(true);
			return result;
		}).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public MessageDetail getMessageDetail(String id, User user) {
		Task task = messageService.findMessageById(id, user);
		if (task == null) {
			return null;
		}
		Activity activity = activityService.findActivityById(task.getBizRefId(), user);
		MessageDetail result = MessageDetail.from(task, activity);
		result.setRead(activityService.isRead(activity, user));
		result.setFavorite(messageService.isFavorite(task, user));
		return result;
	}

	@Override
	public MessageParticipant getMessageParticipant(String id, User user) {
		Task task = messageService.findMessageById(id, user);
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

		return MessageParticipant.from(task.getSender(), user, activityAction);
	}

	@Override
	public long getCountOfAllMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(null);
		return messageService.countOfMessages(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfPendingMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PENDING);
		return messageService.countOfMessages(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfProcessedMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.PROCESSED);
		return messageService.countOfMessages(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfEndedMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		return messageService.countOfMessages(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfNotEndMessages(TaskQueryParameter queryRequest, User user) {
		queryRequest.setTaskStatus(TaskStatus.ENDED);
		return messageService.countOfMessages(queryRequest, TaskQueryRequest.IncludeOrExcludeStatus.EXCLUDE, user);
	}

	@Override
	public long getCountOfFavoriteMessages(PageQueryParameter queryRequest, User user) {
		return messageService.countOfFavoriteMessages(queryRequest, user);
	}

	@Override
	public void addMessageToFavorite(String id, User user) {
		messageService.addMessageToFavorite(id, user);
	}

	@Override
	public void removeMessageFromFavorite(String id, User user) {
		messageService.removeMessageFromFavorite(id, user);
	}


	private MessageSummary convertToMessageSummary(User user, Task task) {
		Activity activity = activityService.findActivityById(task.getBizRefId(), user);
		MessageSummary result = MessageSummary.from(task, activity);
		result.setFavorite(messageService.isFavorite(task, user));
		return result;
	}

}

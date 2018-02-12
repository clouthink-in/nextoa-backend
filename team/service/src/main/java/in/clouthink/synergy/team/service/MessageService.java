package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.domain.model.FavoriteMessage;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import org.springframework.data.domain.Page;

/**
 */
public interface MessageService {

	Page<Task> listMessagesByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user);

	Page<Task> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

	Task findMessageById(String id);

	/**
	 * 根据id查询任务, 通过user判断是否有权限
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	Task findMessageById(String id, User user);

	Page<Task> listMessages(TaskQueryRequest queryParameter,
							TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
							User user);

	Page<Task> listActivityMessages(String bizRefId, PageQueryRequest queryParameter);

	Page<Task> listFavoriteMessages(TaskQueryRequest queryParameter, User user);

	long countOfMessages(TaskQueryRequest queryParameter,
						 TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
						 User user);

	long countOfFavoriteMessages(PageQueryRequest queryParameter, User user);

	FavoriteMessage addMessageToFavorite(String id, User user);

	void removeMessageFromFavorite(String id, User user);

	boolean isFavorite(Task task, User user);

}

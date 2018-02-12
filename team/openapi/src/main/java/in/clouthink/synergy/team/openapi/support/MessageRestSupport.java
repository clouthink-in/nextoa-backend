package in.clouthink.synergy.team.openapi.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.dto.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface MessageRestSupport {

	Page<MessageSummary> listMessagesByTitle(String title, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listMessagesByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user);

	Page<MessageSummary> listAllMessages(TaskQueryParameter queryRequest, User user);

	Page<MessageSummary> listPendingMessages(TaskQueryParameter queryRequest, User user);

	Page<MessageSummary> listProcessedMessages(TaskQueryParameter queryRequest, User user);

	Page<MessageSummary> listNotEndMessages(TaskQueryParameter queryRequest, User user);

	Page<MessageSummary> listEndedMessages(TaskQueryParameter queryRequest, User user);

	Page<MessageSummary> listFavoriteMessages(TaskQueryParameter queryRequest, User user);

	MessageDetail getMessageDetail(String id, User user);

	MessageParticipant getMessageParticipant(String id, User user);

	void addMessageToFavorite(String id, User user);

	void removeMessageFromFavorite(String id, User user);

	long getCountOfAllMessages(TaskQueryParameter queryRequest, User user);

	long getCountOfPendingMessages(TaskQueryParameter queryRequest, User user);

	long getCountOfProcessedMessages(TaskQueryParameter queryRequest, User user);

	long getCountOfEndedMessages(TaskQueryParameter queryRequest, User user);

	long getCountOfNotEndMessages(TaskQueryParameter queryRequest, User user);

	long getCountOfFavoriteMessages(PageQueryParameter queryRequest, User user);

}

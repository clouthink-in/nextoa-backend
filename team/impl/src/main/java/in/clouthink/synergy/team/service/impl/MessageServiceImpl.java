package in.clouthink.synergy.team.service.impl;

import in.clouthink.synergy.account.domain.model.SysRole;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.exception.MessageException;
import in.clouthink.synergy.team.exception.MessageNotFoundException;
import in.clouthink.synergy.team.exception.ActivityException;
import in.clouthink.synergy.team.domain.model.FavoriteMessage;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.repository.FavoriteMessageRepository;
import in.clouthink.synergy.team.repository.MessageRepository;
import in.clouthink.synergy.team.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FavoriteMessageRepository favoriteMessageRepository;

    @Override
    public Page<Task> listMessagesByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user) {
        if (StringUtils.isEmpty(creatorName)) {
            return new PageImpl<>(Collections.emptyList(),
                                  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                                  0);
        }
        if (creatorName.length() < 2) {
            throw new MessageException("为提高搜索结果的准确度,发起人不能少于2个字.");
        }

        return messageRepository.queryPageByActivityCreator(creatorName,
                                                         user,
                                                         new PageRequest(queryParameter.getStart(),
                                                                         queryParameter.getLimit(),
                                                                         new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Page<Task> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user) {
        if (StringUtils.isEmpty(receiverName)) {
            return new PageImpl<>(Collections.emptyList(),
                                  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                                  0);
        }
        if (receiverName.length() < 2) {
            throw new MessageException("为提高搜索结果的准确度,接收人不能少于2个字.");
        }

        return messageRepository.queryPageByReceiver(receiverName,
                                                     user,
                                                     new PageRequest(queryParameter.getStart(),
                                                                     queryParameter.getLimit(),
                                                                     new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Task findMessageById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public Task findMessageById(String id, User user) {
        Task task = messageRepository.findById(id);
        if (task == null) {
            return null;
        }

        if (user.getAuthorities().contains(SysRole.ROLE_ADMIN) || user.getAuthorities().contains(SysRole.ROLE_MGR)) {
            return task;
        }
        if (task.getReceiver().getId().equals(user.getId())) {
            return task;
        }

        throw new ActivityException("无查看该任务的权限");
    }

    @Override
    public Page<Task> listMessages(TaskQueryRequest request,
                                   TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                   User user) {
        return messageRepository.queryPage(user, request, includeOrExcludeStatus);
    }

    @Override
    public Page<Task> listActivityMessages(String bizRefId, PageQueryRequest queryParameter) {
        return messageRepository.findPageByBizRefId(bizRefId,
                                                    new PageRequest(queryParameter.getStart(),
                                                                    queryParameter.getLimit(),
                                                                    new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Page<Task> listFavoriteMessages(TaskQueryRequest request, User user) {
        Pageable pageable = new PageRequest(request.getStart(), request.getLimit());
        Page<FavoriteMessage> favoriteMessages = favoriteMessageRepository.queryPage(user, request);
        List<Task> tasks = favoriteMessages.getContent()
                                           .stream()
                                           .map(favoriteMessage -> favoriteMessage.getTask())
                                           .collect(Collectors.toList());
        return new PageImpl<>(tasks, pageable, favoriteMessages.getTotalElements());
    }

    @Override
    public long countOfMessages(TaskQueryRequest request,
                                TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                User user) {
        return messageRepository.queryCount(user, request, includeOrExcludeStatus);
    }

    @Override
    public long countOfFavoriteMessages(PageQueryRequest request, User user) {
        return favoriteMessageRepository.countByCreatedBy(user);
    }

    @Override
    public FavoriteMessage addMessageToFavorite(String id, User user) {
        Task task = messageRepository.findById(id);
        if (task == null) {
            throw new MessageNotFoundException(id);
        }
        FavoriteMessage favoriteMessage = favoriteMessageRepository.findByMessageAndCreatedBy(task, user);
        if (favoriteMessage != null) {
            return favoriteMessage;
        }
        favoriteMessage = new FavoriteMessage();
        favoriteMessage.setTask(task);
        favoriteMessage.setCreatedBy(user);
        favoriteMessage.setCreatedAt(new Date());
        return favoriteMessageRepository.save(favoriteMessage);
    }

    @Override
    public void removeMessageFromFavorite(String id, User user) {
        Task task = messageRepository.findById(id);
        if (task == null) {
            throw new MessageNotFoundException(id);
        }
        FavoriteMessage favoriteMessage = favoriteMessageRepository.findByMessageAndCreatedBy(task, user);
        if (favoriteMessage == null) {
            return;
        }
        favoriteMessageRepository.delete(favoriteMessage);
    }

    @Override
    public boolean isFavorite(Task task, User user) {
        return favoriteMessageRepository.findByMessageAndCreatedBy(task, user) != null;
    }

}

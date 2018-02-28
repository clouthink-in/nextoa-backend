package in.clouthink.synergy.team.service.impl;

import in.clouthink.synergy.account.domain.model.SysRole;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import in.clouthink.synergy.team.domain.model.FavoriteTask;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.exception.ActivityException;
import in.clouthink.synergy.team.exception.TaskException;
import in.clouthink.synergy.team.exception.TaskNotFoundException;
import in.clouthink.synergy.team.repository.FavoriteTaskRepository;
import in.clouthink.synergy.team.repository.TaskRepository;
import in.clouthink.synergy.team.service.TaskService;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FavoriteTaskRepository favoriteTaskRepository;

    @Override
    public Page<Task> listTasksByActivityCreator(String creatorName, PageQueryRequest queryParameter, User user) {
        if (StringUtils.isEmpty(creatorName)) {
            return new PageImpl<>(Collections.emptyList(),
                                  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                                  0);
        }
        if (creatorName.length() < 2) {
            throw new TaskException("为提高搜索结果的准确度,发起人不能少于2个字.");
        }

        return taskRepository.queryPageByActivityCreator(creatorName,
                                                         user,
                                                         new PageRequest(queryParameter.getStart(),
                                                                         queryParameter.getLimit(),
                                                                         new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Page<Task> listTasksByReceiver(String receiverName, PageQueryRequest queryParameter, User user) {
        if (StringUtils.isEmpty(receiverName)) {
            return new PageImpl<>(Collections.emptyList(),
                                  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                                  0);
        }
        if (receiverName.length() < 2) {
            throw new TaskException("为提高搜索结果的准确度,接收人不能少于2个字.");
        }

        return taskRepository.queryPageByReceiver(receiverName,
                                                  user,
                                                  new PageRequest(queryParameter.getStart(),
                                                                     queryParameter.getLimit(),
                                                                     new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Task findTaskById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task findTaskById(String id, User user) {
        Task task = taskRepository.findById(id);
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
    public Page<Task> listTasks(TaskQueryRequest request,
                                TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                                User user) {
        return taskRepository.queryPage(user, request, includeOrExcludeStatus);
    }

    @Override
    public Page<Task> listActiveTasks(String bizRefId, PageQueryRequest queryParameter) {
        return taskRepository.findPageByBizRefId(bizRefId,
                                                 new PageRequest(queryParameter.getStart(),
                                                                    queryParameter.getLimit(),
                                                                    new Sort(Sort.Direction.DESC, "receivedAt")));
    }

    @Override
    public Page<Task> listFavoriteTasks(TaskQueryRequest request, User user) {
        Pageable pageable = new PageRequest(request.getStart(), request.getLimit());
        Page<FavoriteTask> favoriteMessages = favoriteTaskRepository.queryPage(user, request);
        List<Task> tasks = favoriteMessages.getContent()
                                           .stream()
                                           .map(favoriteMessage -> favoriteMessage.getTask())
                                           .collect(Collectors.toList());
        return new PageImpl<>(tasks, pageable, favoriteMessages.getTotalElements());
    }

    @Override
    public long countOfTasks(TaskQueryRequest request,
                             TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
                             User user) {
        return taskRepository.queryCount(user, request, includeOrExcludeStatus);
    }

    @Override
    public long countOfFavoriteTasks(PageQueryRequest request, User user) {
        return favoriteTaskRepository.countByCreatedBy(user);
    }

    @Override
    public FavoriteTask addTaskToFavorite(String id, User user) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        FavoriteTask favoriteTask = favoriteTaskRepository.findByTaskAndCreatedBy(task, user);
        if (favoriteTask != null) {
            return favoriteTask;
        }
        favoriteTask = new FavoriteTask();
        favoriteTask.setTask(task);
        favoriteTask.setCreatedBy(user);
        favoriteTask.setCreatedAt(new Date());
        return favoriteTaskRepository.save(favoriteTask);
    }

    @Override
    public void removeTaskFromFavorite(String id, User user) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        FavoriteTask favoriteTask = favoriteTaskRepository.findByTaskAndCreatedBy(task, user);
        if (favoriteTask == null) {
            return;
        }
        favoriteTaskRepository.delete(favoriteTask);
    }

    @Override
    public boolean isFavorite(Task task, User user) {
        return favoriteTaskRepository.findByTaskAndCreatedBy(task, user) != null;
    }

}

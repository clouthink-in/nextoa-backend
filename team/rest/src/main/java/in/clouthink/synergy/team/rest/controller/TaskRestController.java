package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.TaskCreatorNameSearchParam;
import in.clouthink.synergy.team.rest.param.TaskReceiverNameSearchParam;
import in.clouthink.synergy.team.rest.param.TaskSearchParam;
import in.clouthink.synergy.team.rest.param.TaskTitleSearchParam;
import in.clouthink.synergy.team.rest.support.TaskRestSupport;
import in.clouthink.synergy.team.rest.view.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Api("我的任务")
@RestController
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private TaskRestSupport taskRestSupport;

    @ApiOperation(value = "快速查询-标题")
    @GetMapping(value = "/tasks/byTitle")
    public Page<TaskView> listTasksByTitle(TaskTitleSearchParam queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listTasksByTitle(queryParameter.getTitle(), queryParameter, user);
    }

    @ApiOperation(value = "快速查询-发起人")
    @GetMapping(value = "/tasks/byCreator")
    public Page<TaskView> listTasksByActivityCreator(TaskCreatorNameSearchParam queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listTasksByActivityCreator(queryParameter.getCreatorName(), queryParameter, user);
    }

    @ApiOperation(value = "快速查询-接收人")
    @GetMapping(value = "/tasks/byReceiver")
    public Page<TaskView> listTasksByReceiver(TaskReceiverNameSearchParam queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listTasksByReceiver(queryParameter.getReceiverName(), queryParameter, user);
    }

    @ApiOperation(value = "我的所有任务（不区分状态）,支持分页,支持动态查询")
    @GetMapping(value = "/tasks")
    public Page<TaskView> listAllTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listAllTasks(queryRequest, user);
    }

    @ApiOperation(value = "我的待处理任务,支持分页,支持动态查询")
    @GetMapping(value = "/tasks/pending")
    public Page<TaskView> listPendingTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listPendingTasks(queryRequest, user);
    }

    @ApiOperation(value = "我的已处理任务,支持分页,支持动态查询")
    @GetMapping(value = "/tasks/processed")
    public Page<TaskView> listProcessedTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listProcessedTasks(queryRequest, user);
    }

    @ApiOperation(value = "我的已结束任务,支持分页,支持动态查询")
    @GetMapping(value = "/tasks/ended")
    public Page<TaskView> listEndedTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listEndedTasks(queryRequest, user);
    }

    @ApiOperation(value = "我的未结束任务,支持分页,支持动态查询")
    @GetMapping(value = "/tasks/notend")
    public Page<TaskView> listNotEndTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listNotEndTasks(queryRequest, user);
    }

    @ApiOperation(value = "我收藏的任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/favorite")
    public Page<TaskView> listFavoriteTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.listFavoriteTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的所有任务（不区分状态）, 支持动态查询")
    @GetMapping(value = "/tasks/countOfAll")
    public long countOfAllTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfAllTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的待处理任务, 支持动态查询")
    @GetMapping(value = "/tasks/countOfPending")
    public long countOfPendingTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfPendingTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的已处理任务, 支持动态查询")
    @GetMapping(value = "/tasks/countOfProcessed")
    public long countOfProcessedTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfProcessedTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 已结束的任务, 支持动态查询")
    @GetMapping(value = "/tasks/countOfEnded")
    public long countOfEndedTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfEndedTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 未结束的任务, 支持动态查询")
    @GetMapping(value = "/tasks/countOfNotEnd")
    public long countOfNotEndTasks(TaskSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfNotEndTasks(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我收藏的任务, 支持动态查询")
    @GetMapping(value = "/tasks/countOfFavorite")
    public long countOfFavoriteTasks(PageSearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getCountOfFavoriteTasks(queryRequest, user);
    }

    @ApiOperation(value = "查看任务（任务）明细")
    @GetMapping(value = "/tasks/{id}")
    public TaskDetailView getTaskDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getTaskDetail(id, user);
    }

    @ApiOperation(value = "查看任务（任务）- 参与人")
    @GetMapping(value = "/tasks/{id}/participant")
    public TaskParticipantView getTaskParticipant(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return taskRestSupport.getTaskParticipant(id, user);
    }

    @ApiOperation(value = "收藏任务（重复收藏自动忽略）")
    @PostMapping(value = "/tasks/{id}/favorite")
    public void addTaskToFavorite(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        taskRestSupport.addTaskToFavorite(id, user);
    }

    @ApiOperation(value = "取消收藏任务（重复取消自动忽略）")
    @DeleteMapping(value = "/tasks/{id}/favorite")
    public void removeTaskFromFavorite(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        taskRestSupport.removeTaskFromFavorite(id, user);
    }

}

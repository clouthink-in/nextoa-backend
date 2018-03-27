package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.ActivityActionSearchParam;
import in.clouthink.synergy.team.rest.view.*;
import in.clouthink.synergy.team.rest.support.RepositoryRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("协作请求库（系统管理中中监控所有的协作请求,但是不能操作,只能查看）")
@RestController
@RequestMapping("/api/repo/activities")
public class RepositoryRestController {

    @Autowired
    private RepositoryRestSupport repositoryRestSupport;

    @ApiOperation(value = "列出系统的所有协作请求（不区分状态）,支持分页,支持动态查询")
    @GetMapping()
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest) {
        return repositoryRestSupport.listAllActivities(queryRequest);
    }

    @ApiOperation(value = "查看协作请求详情")
    @GetMapping(value = "/{id}")
    public ActivityDetailView getActivityDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return repositoryRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求权限（对发起人无效,发起人自动拥有所有权限）")
    @GetMapping(value = "/{id}/allowed-actions")
    public List<String> getActivityAllowedActions(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return repositoryRestSupport.getActivityAllowedActions(id, user);
    }

    @ApiOperation(value = "查看协作请求的阅读历史,支持分页,按阅读时间逆序排列")
    @GetMapping(value = "/{id}/read-history")
    public Page<ActivityReadView> getActivityReadHistory(@PathVariable String id,
                                                         ActivityActionSearchParam queryRequest) {
        return repositoryRestSupport.getActivityReadHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的打印历史,支持分页,按打印时间逆序排列")
    @GetMapping(value = "/{id}/print-history")
    public Page<ActivityPrintView> getActivityPrintHistory(@PathVariable String id,
                                                           ActivityActionSearchParam queryRequest) {
        return repositoryRestSupport.getActivityPrintHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的流转情况,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/{id}/transition-history")
    public Page<ActivityTransitionView> getActivityTransitionHistory(@PathVariable String id,
                                                                     ActivityActionSearchParam queryRequest) {
        return repositoryRestSupport.getActivityTransitionHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理情况,支持分页,按处理时间逆序排列")
    @GetMapping(value = "/{id}/process-history")
    public Page<ActivityProcessView> getActivityProcessHistory(@PathVariable String id,
                                                               ActivityActionSearchParam queryRequest) {
        return repositoryRestSupport.getActivityProcessHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理情况,支持分页,按处理时间逆序排列（不分页)")
    @GetMapping(value = "/{id}/process-history/list")
    public List<ActivityProcessView> getActivityProcessHistoryList(@PathVariable String id) {
        return repositoryRestSupport.getActivityProcessHistory(id);
    }

    @ApiOperation(value = "协作请求任务跟踪,查看协作请求启动后的所有任务列表,只关心最近的状态,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/{id}/tasks")
    public Page<ActivityTaskView> getActivityTasks(@PathVariable String id, PageSearchParam queryRequest) {
        return repositoryRestSupport.getActivityTasks(id, queryRequest);
    }

    @ApiOperation(value = "终止协作请求-对应的所有任务为终止状态")
    @PostMapping(value = "/{id}/terminate")
    public void terminateActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        repositoryRestSupport.terminateActivity(id, user);
    }

}

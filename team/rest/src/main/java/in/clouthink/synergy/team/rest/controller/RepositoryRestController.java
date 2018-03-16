package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.SysActivityRestSupport;
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
@RequestMapping("/api/repos")
public class RepositoryRestController {

    @Autowired
    private SysActivityRestSupport sysActivityRestSupport;

    @ApiOperation(value = "列出系统的所有协作请求（不区分状态）,支持分页,支持动态查询")
    @GetMapping(value = "/activities")
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest) {
        return sysActivityRestSupport.listAllActivities(queryRequest);
    }

    @ApiOperation(value = "查看协作请求详情")
    @GetMapping(value = "/activities/{id}")
    public ActivityDetail getActivityDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return sysActivityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求权限（对发起人无效,发起人自动拥有所有权限）")
    @GetMapping(value = "/activities/{id}/allowedActions")
    public List<String> getActivityAllowedActions(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return sysActivityRestSupport.getActivityAllowedActions(id, user);
    }

    @ApiOperation(value = "查看协作请求的阅读历史,支持分页,按阅读时间逆序排列")
    @GetMapping(value = "/activities/{id}/readHistory")
    public Page<ActivityReadSummary> getActivityReadHistory(@PathVariable String id,
                                                            ActivityActionQueryParameter queryRequest) {
        return sysActivityRestSupport.getActivityReadHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的打印历史,支持分页,按打印时间逆序排列")
    @GetMapping(value = "/activities/{id}/printHistory")
    public Page<ActivityPrintSummary> getActivityPrintHistory(@PathVariable String id,
                                                              ActivityActionQueryParameter queryRequest) {
        return sysActivityRestSupport.getActivityPrintHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的流转情况,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/activities/{id}/transitionHistory")
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(@PathVariable String id,
                                                                        ActivityActionQueryParameter queryRequest) {
        return sysActivityRestSupport.getActivityTransitionHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理情况,支持分页,按处理时间逆序排列")
    @GetMapping(value = "/activities/{id}/processHistory")
    public Page<ActivityProcessSummary> getActivityProcessHistory(@PathVariable String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        return sysActivityRestSupport.getActivityProcessHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理情况,支持分页,按处理时间逆序排列（不分页)")
    @GetMapping(value = "/activities/{id}/processHistory/list")
    public List<ActivityProcessSummary> getActivityProcessHistoryList(@PathVariable String id) {
        return sysActivityRestSupport.getActivityProcessHistory(id);
    }

    @ApiOperation(value = "协作请求任务跟踪,查看协作请求启动后的所有任务列表,只关心最近的状态,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/activities/{id}/tasks")
    public Page<ActivityTaskSummary> getActivityTasks(@PathVariable String id, PageQueryParameter queryRequest) {
        return sysActivityRestSupport.getActivityTasks(id, queryRequest);
    }

    @ApiOperation(value = "终止协作请求-对应的所有任务为终止状态")
    @PostMapping(value = "/activities/{id}/terminate")
    public void terminateActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        sysActivityRestSupport.terminateActivity(id, user);
    }

}

package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdAndValue;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.synergy.team.rest.param.*;
import in.clouthink.synergy.team.rest.view.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/api/activities", description = "我的协作请求（协作请求）")
@RestController
@RequestMapping("/api/activities")
public class ActivityRestController {

    @Autowired
    private ActivityRestSupport activityRestSupport;

    @ApiOperation(value = "列出我的所有协作请求（不区分状态）,支持分页,支持动态查询")
    @GetMapping()
    public Page<ActivityView> listAllActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（只是草稿状态）,支持分页,支持动态查询")
    @GetMapping(value = "/draft")
    public Page<ActivityView> listDraftActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（流转中）,支持分页,支持动态查询")
    @GetMapping(value = "/processing")
    public Page<ActivityView> listProcessingActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（撤回状态）,支持分页,支持动态查询")
    @GetMapping(value = "/revoked")
    public Page<ActivityView> listRevokedActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的所有协作请求（不区分状态）,支持动态查询")
    @GetMapping(value = "/count-of-all")
    public long countOfAllActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（只是草稿状态）,支持动态查询")
    @GetMapping(value = "/count-of-draft")
    public long countOfDraftActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（流转中）,支持动态查询")
    @GetMapping(value = "/count-of-processing")
    public long countOfProcessingActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（撤回状态）,支持动态查询")
    @GetMapping(value = "/count-of-revoked")
    public long countOfRevokedActivities(ActivitySearchParam queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "查看协作请求详情")
    @GetMapping(value = "/{id}")
    public ActivityDetailView getActivityDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "打印协作请求（需要有打印权限）")
    @PostMapping(value = "/{id}/print")
    public ActivityDetailView printActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.printActivity(id, user);
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求权限（对发起人无效,发起人自动拥有所有权限）")
    @GetMapping(value = "/{id}/allowed-actions")
    public List<String> getActivityAllowedActions(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityAllowedActions(id, user);
    }

    @ApiOperation(value = "新增协作请求（草稿状态,可以反复修改）")
    @PostMapping()
    public IdAndValue createActivity(@RequestBody SaveActivityParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdAndValue.from(activityRestSupport.createActivity(request, user));
    }

    @ApiOperation(value = "修改协作请求（草稿,撤回的状态才可以修改）")
    @PostMapping(value = "/{id}")
    public void updateActivity(@PathVariable String id, @RequestBody SaveActivityParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.updateActivity(id, request, user);
    }

    @ApiOperation(value = "删除协作请求（草稿,撤回状态的才可以删除）")
    @DeleteMapping(value = "/{id}")
    public void deleteActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.deleteActivity(id, user);
    }

    @ApiOperation(value = "提交协作请求到流程中（草稿,撤回状态的可以提交）")
    @PostMapping(value = "/{id}/start")
    public void startActivity(@PathVariable String id, @RequestBody StartActivityParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.startActivity(id, request, user);
    }

    @ApiOperation(value = "撤回协作请求（流转中,且没有被处理的）")
    @PostMapping(value = "/{id}/revoke")
    public void revokeActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.revokeActivity(id, user);
    }

    @ApiOperation(value = "标记协作请求为已读")
    @PostMapping(value = "/{id}/read")
    public void markActivityAsRead(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsRead(id, user);
    }

    @ApiOperation(value = "回复协作请求")
    @PostMapping(value = "/{id}/reply")
    public void replyActivity(@PathVariable String id, @RequestBody ReplyActivityParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.replyActivity(id, request, user);
    }

    @ApiOperation(value = "转发协作请求（需要有转发权限）")
    @PostMapping(value = "/{id}/forward")
    public void forwardActivity(@PathVariable String id, @RequestBody ForwardActivityParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.forwardActivity(id, request, user);
    }

    @ApiOperation(value = "结束协作请求-对应的任务为结束状态（注意:回复,转发后,用户的任务也为结束状态）")
    @PostMapping(value = "/{id}/done")
    public void markActivityAsDone(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsDone(id, user);
    }

    @ApiOperation(value = "复制协作请求（原协作请求允许再处理）")
    @PostMapping(value = "/{id}/copy")
    public ActivityDetailView copyActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.copyActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求的阅读历史,支持分页,按阅读时间逆序排列")
    @GetMapping(value = "/{id}/read-history")
    public Page<ActivityReadView> getActivityReadHistory(@PathVariable String id,
                                                         ActivityActionSearchParam queryRequest) {
        return activityRestSupport.getActivityReadHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的打印历史,支持分页,按打印时间逆序排列")
    @GetMapping(value = "/{id}/print-history")
    public Page<ActivityPrintView> getActivityPrintHistory(@PathVariable String id,
                                                           ActivityActionSearchParam queryRequest) {
        return activityRestSupport.getActivityPrintHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的流转情况,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/{id}/transition-history")
    public Page<ActivityTransitionView> getActivityTransitionHistory(@PathVariable String id,
                                                                     ActivityActionSearchParam queryRequest) {
        return activityRestSupport.getActivityTransitionHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看哪些用户进行了结束协作请求操作,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/{id}/end-history")
    public Page<ActivityTransitionView> getActivityEndHistory(@PathVariable String id,
                                                              ActivityActionSearchParam queryRequest) {
        return activityRestSupport.getActivityEndHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,支持分页,按处理时间逆序排列")
    @GetMapping(value = "/{id}/process-history")
    public Page<ActivityProcessView> getActivityProcessHistory(@PathVariable String id,
                                                               ActivityActionSearchParam queryRequest) {
        return activityRestSupport.getActivityProcessHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,按处理时间逆序排列（不分页)")
    @GetMapping(value = "/{id}/process-history-list")
    public List<ActivityProcessView> getActivityProcessHistoryList(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityProcessHistory(id, user);
    }

    @ApiOperation(value = "协作请求任务跟踪,查看协作请求启动后的所有任务列表,只关心最近的状态,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/{id}/tasks")
    public Page<ActivityTaskView> getActivityTasks(@PathVariable String id, PageSearchParam queryRequest) {
        return activityRestSupport.getActivityTasks(id, queryRequest);
    }

}

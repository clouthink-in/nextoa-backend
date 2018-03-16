package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.model.IdValuePair;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.rest.dto.*;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("我的协作请求（协作请求）")
@RestController
@RequestMapping("/api")
public class ActivityRestController {

    @Autowired
    private ActivityRestSupport activityRestSupport;

    @ApiOperation(value = "列出我的所有协作请求（不区分状态）,支持分页,支持动态查询")
    @GetMapping(value = "/activities")
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（只是草稿状态）,支持分页,支持动态查询")
    @GetMapping(value = "/activities/draft")
    public Page<ActivitySummary> listDraftActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（流转中）,支持分页,支持动态查询")
    @GetMapping(value = "/activities/processing")
    public Page<ActivitySummary> listProcessingActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（撤回状态）,支持分页,支持动态查询")
    @GetMapping(value = "/activities/revoked")
    public Page<ActivitySummary> listRevokedActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的所有协作请求（不区分状态）,支持动态查询")
    @GetMapping(value = "/activities/countOfAll")
    public long countOfAllActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（只是草稿状态）,支持动态查询")
    @GetMapping(value = "/activities/countOfDraft")
    public long countOfDraftActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（流转中）,支持动态查询")
    @GetMapping(value = "/activities/countOfProcessing")
    public long countOfProcessingActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（撤回状态）,支持动态查询")
    @GetMapping(value = "/activities/countOfRevoked")
    public long countOfRevokedActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "查看协作请求详情")
    @GetMapping(value = "/activities/{id}")
    public ActivityDetail getActivityDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "打印协作请求（需要有打印权限）")
    @PostMapping(value = "/activities/{id}/print")
    public ActivityDetail printActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.printActivity(id, user);
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求权限（对发起人无效,发起人自动拥有所有权限）")
    @GetMapping(value = "/activities/{id}/allowedActions")
    public List<String> getActivityAllowedActions(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityAllowedActions(id, user);
    }

    @ApiOperation(value = "新增协作请求（草稿状态,可以反复修改）")
    @PostMapping(value = "/activities")
    public IdValuePair createActivity(@RequestBody SaveActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return IdValuePair.from(activityRestSupport.createActivity(request, user));
    }

    @ApiOperation(value = "修改协作请求（草稿,撤回的状态才可以修改）")
    @PostMapping(value = "/activities/{id}")
    public void updateActivity(@PathVariable String id, @RequestBody SaveActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.updateActivity(id, request, user);
    }

    @ApiOperation(value = "删除协作请求（草稿,撤回状态的才可以删除）")
    @DeleteMapping(value = "/activities/{id}")
    public void deleteActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.deleteActivity(id, user);
    }

    @ApiOperation(value = "提交协作请求到流程中（草稿,撤回状态的可以提交）")
    @PostMapping(value = "/activities/{id}/start")
    public void startActivity(@PathVariable String id, @RequestBody StartActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.startActivity(id, request, user);
    }

    @ApiOperation(value = "撤回协作请求（流转中,且没有被处理的）")
    @PostMapping(value = "/activities/{id}/revoke")
    public void revokeActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.revokeActivity(id, user);
    }

    @ApiOperation(value = "标记协作请求为已读")
    @PostMapping(value = "/activities/{id}/read")
    public void markActivityAsRead(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsRead(id, user);
    }

    @ApiOperation(value = "回复协作请求")
    @PostMapping(value = "/activities/{id}/reply")
    public void replyActivity(@PathVariable String id, @RequestBody ReplyActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.replyActivity(id, request, user);
    }

    @ApiOperation(value = "转发协作请求（需要有转发权限）")
    @PostMapping(value = "/activities/{id}/forward")
    public void forwardActivity(@PathVariable String id, @RequestBody ForwardActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.forwardActivity(id, request, user);
    }

    @ApiOperation(value = "结束协作请求-对应的任务为结束状态（注意:回复,转发后,用户的任务也为结束状态）")
    @PostMapping(value = "/activities/{id}/done")
    public void markActivityAsDone(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsDone(id, user);
    }

    @ApiOperation(value = "复制协作请求（原协作请求允许再处理）")
    @PostMapping(value = "/activities/{id}/copy")
    public ActivityDetail copyActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.copyActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求的阅读历史,支持分页,按阅读时间逆序排列")
    @GetMapping(value = "/activities/{id}/readHistory")
    public Page<ActivityReadSummary> getActivityReadHistory(@PathVariable String id,
                                                            ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityReadHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的打印历史,支持分页,按打印时间逆序排列")
    @GetMapping(value = "/activities/{id}/printHistory")
    public Page<ActivityPrintSummary> getActivityPrintHistory(@PathVariable String id,
                                                              ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityPrintHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的流转情况,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/activities/{id}/transitionHistory")
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(@PathVariable String id,
                                                                        ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityTransitionHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看哪些用户进行了结束协作请求操作,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/activities/{id}/endHistory")
    public Page<ActivityTransitionSummary> getActivityEndHistory(@PathVariable String id,
                                                                 ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityEndHistory(id, queryRequest);
    }

    @ApiOperation(value = "协作请求任务跟踪,查看协作请求启动后的所有任务列表,只关心最近的状态,支持分页,按流转时间逆序排列")
    @GetMapping(value = "/activities/{id}/messages")
    public Page<ActivityTaskSummary> getActivityMessages(@PathVariable String id, PageQueryParameter queryRequest) {
        return activityRestSupport.getActivityMessages(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,支持分页,按处理时间逆序排列")
    @GetMapping(value = "/activities/{id}/processHistory")
    public Page<ActivityProcessSummary> getActivityProcessHistory(@PathVariable String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityProcessHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,按处理时间逆序排列（不分页)")
    @GetMapping(value = "/activities/{id}/processHistory/list")
    public List<ActivityProcessSummary> getActivityProcessHistoryList(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityProcessHistory(id, user);
    }

}

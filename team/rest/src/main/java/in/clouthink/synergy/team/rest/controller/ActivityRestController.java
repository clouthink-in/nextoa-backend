package in.clouthink.synergy.team.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.rest.support.ActivityRestSupport;
import in.clouthink.synergy.team.rest.dto.*;
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
    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public Page<ActivitySummary> listAllActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（只是草稿状态）,支持分页,支持动态查询")
    @RequestMapping(value = "/activities/draft", method = RequestMethod.GET)
    public Page<ActivitySummary> listDraftActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（流转中）,支持分页,支持动态查询")
    @RequestMapping(value = "/activities/processing", method = RequestMethod.GET)
    public Page<ActivitySummary> listProcessingActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "列出我的协作请求（撤回状态）,支持分页,支持动态查询")
    @RequestMapping(value = "/activities/revoked", method = RequestMethod.GET)
    public Page<ActivitySummary> listRevokedActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.listRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的所有协作请求（不区分状态）,支持动态查询")
    @RequestMapping(value = "/activities/countOfAll", method = RequestMethod.GET)
    public long countOfAllActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfAllActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（只是草稿状态）,支持动态查询")
    @RequestMapping(value = "/activities/countOfDraft", method = RequestMethod.GET)
    public long countOfDraftActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfDraftActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（流转中）,支持动态查询")
    @RequestMapping(value = "/activities/countOfProcessing", method = RequestMethod.GET)
    public long countOfProcessingActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfProcessingActivities(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的协作请求（撤回状态）,支持动态查询")
    @RequestMapping(value = "/activities/countOfRevoked", method = RequestMethod.GET)
    public long countOfRevokedActivities(ActivityQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.countOfRevokedActivities(queryRequest, user);
    }

    @ApiOperation(value = "查看协作请求详情")
    @RequestMapping(value = "/activities/{id}", method = RequestMethod.GET)
    public ActivityDetail getActivityDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "打印协作请求（需要有打印权限）")
    @RequestMapping(value = "/activities/{id}/print", method = RequestMethod.POST)
    public ActivityDetail printActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.printActivity(id, user);
        return activityRestSupport.getActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求权限（对发起人无效,发起人自动拥有所有权限）")
    @RequestMapping(value = "/activities/{id}/allowedActions", method = RequestMethod.GET)
    public List<String> getActivityAllowedActions(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityAllowedActions(id, user);
    }

    @ApiOperation(value = "新增协作请求（草稿状态,可以反复修改）")
    @RequestMapping(value = "/activities", method = RequestMethod.POST)
    public String createActivity(@RequestBody SaveActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.createActivity(request, user);
    }

    @ApiOperation(value = "修改协作请求（草稿,撤回的状态才可以修改）")
    @RequestMapping(value = "/activities/{id}", method = RequestMethod.POST)
    public void updateActivity(@PathVariable String id, @RequestBody SaveActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.updateActivity(id, request, user);
    }

    @ApiOperation(value = "删除协作请求（草稿,撤回状态的才可以删除）")
    @RequestMapping(value = "/activities/{id}", method = RequestMethod.DELETE)
    public void deleteActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.deleteActivity(id, user);
    }

    @ApiOperation(value = "提交协作请求到流程中（草稿,撤回状态的可以提交）")
    @RequestMapping(value = "/activities/{id}/start", method = RequestMethod.POST)
    public void startActivity(@PathVariable String id, @RequestBody StartActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.startActivity(id, request, user);
    }

    @ApiOperation(value = "撤回协作请求（流转中,且没有被处理的）")
    @RequestMapping(value = "/activities/{id}/revoke", method = RequestMethod.POST)
    public void revokeActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.revokeActivity(id, user);
    }

    @ApiOperation(value = "标记协作请求为已读")
    @RequestMapping(value = "/activities/{id}/read", method = RequestMethod.POST)
    public void markActivityAsRead(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsRead(id, user);
    }

    @ApiOperation(value = "回复协作请求")
    @RequestMapping(value = "/activities/{id}/reply", method = RequestMethod.POST)
    public void replyActivity(@PathVariable String id, @RequestBody ReplyActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.replyActivity(id, request, user);
    }

    @ApiOperation(value = "转发协作请求（需要有转发权限）")
    @RequestMapping(value = "/activities/{id}/forward", method = RequestMethod.POST)
    public void forwardActivity(@PathVariable String id, @RequestBody ForwardActivityParameter request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.forwardActivity(id, request, user);
    }

    @ApiOperation(value = "结束协作请求-对应的任务为结束状态（注意:回复,转发后,用户的任务也为结束状态）")
    @RequestMapping(value = "/activities/{id}/done", method = RequestMethod.POST)
    public void markActivityAsDone(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        activityRestSupport.markActivityAsDone(id, user);
    }

    @ApiOperation(value = "复制协作请求（原协作请求允许再处理）")
    @RequestMapping(value = "/activities/{id}/copy", method = RequestMethod.POST)
    public ActivityDetail copyActivity(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.copyActivityDetail(id, user);
    }

    @ApiOperation(value = "查看协作请求的阅读历史,支持分页,按阅读时间逆序排列")
    @RequestMapping(value = "/activities/{id}/readHistory", method = RequestMethod.GET)
    public Page<ActivityReadSummary> getActivityReadHistory(@PathVariable String id, ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityReadHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的打印历史,支持分页,按打印时间逆序排列")
    @RequestMapping(value = "/activities/{id}/printHistory", method = RequestMethod.GET)
    public Page<ActivityPrintSummary> getActivityPrintHistory(@PathVariable String id,
                                                        ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityPrintHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的流转情况,支持分页,按流转时间逆序排列")
    @RequestMapping(value = "/activities/{id}/transitionHistory", method = RequestMethod.GET)
    public Page<ActivityTransitionSummary> getActivityTransitionHistory(@PathVariable String id,
                                                                  ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityTransitionHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看哪些用户进行了结束协作请求操作,支持分页,按流转时间逆序排列")
    @RequestMapping(value = "/activities/{id}/endHistory", method = RequestMethod.GET)
    public Page<ActivityTransitionSummary> getActivityEndHistory(@PathVariable String id,
                                                           ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityEndHistory(id, queryRequest);
    }

    @ApiOperation(value = "协作请求任务跟踪,查看协作请求启动后的所有任务列表,只关心最近的状态,支持分页,按流转时间逆序排列")
    @RequestMapping(value = "/activities/{id}/messages", method = RequestMethod.GET)
    public Page<ActivityTaskSummary> getActivityMessages(@PathVariable String id, PageQueryParameter queryRequest) {
        return activityRestSupport.getActivityMessages(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,支持分页,按处理时间逆序排列")
    @RequestMapping(value = "/activities/{id}/processHistory", method = RequestMethod.GET)
    public Page<ActivityProcessSummary> getActivityProcessHistory(@PathVariable String id,
                                                            ActivityActionQueryParameter queryRequest) {
        return activityRestSupport.getActivityProcessHistory(id, queryRequest);
    }

    @ApiOperation(value = "查看协作请求的处理意见,按处理时间逆序排列（不分页)")
    @RequestMapping(value = "/activities/{id}/processHistory/list", method = RequestMethod.GET)
    public List<ActivityProcessSummary> getActivityProcessHistoryList(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return activityRestSupport.getActivityProcessHistory(id, user);
    }

}

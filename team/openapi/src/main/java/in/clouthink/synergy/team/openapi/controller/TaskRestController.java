package in.clouthink.synergy.team.openapi.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageQueryParameter;
import in.clouthink.synergy.team.openapi.support.MessageRestSupport;
import in.clouthink.synergy.team.openapi.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api("我的任务")
@RestController
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private MessageRestSupport messageRestSupport;

    @ApiOperation(value = "快速查询-标题")
    @RequestMapping(value = "/tasks/byTitle", method = RequestMethod.GET)
    public Page<MessageSummary> listMessagesByTitle(MessageTitleQueryParameter queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listMessagesByTitle(queryParameter.getTitle(), queryParameter, user);
    }

    @ApiOperation(value = "快速查询-发起人")
    @RequestMapping(value = "/tasks/byCreator", method = RequestMethod.GET)
    public Page<MessageSummary> listMessagesByActivityCreator(MessageCreatorNameQueryParameter queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listMessagesByActivityCreator(queryParameter.getCreatorName(), queryParameter, user);
    }

    @ApiOperation(value = "快速查询-接收人")
    @RequestMapping(value = "/tasks/byReceiver", method = RequestMethod.GET)
    public Page<MessageSummary> listMessagesByReceiver(MessageReceiverNameQueryParameter queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listMessagesByReceiver(queryParameter.getReceiverName(), queryParameter, user);
    }

    @ApiOperation(value = "我的所有任务（不区分状态）,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Page<MessageSummary> listAllMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listAllMessages(queryRequest, user);
    }

    @ApiOperation(value = "我的待处理任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/pending", method = RequestMethod.GET)
    public Page<MessageSummary> listPendingMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listPendingMessages(queryRequest, user);
    }

    @ApiOperation(value = "我的已处理任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/processed", method = RequestMethod.GET)
    public Page<MessageSummary> listProcessedMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listProcessedMessages(queryRequest, user);
    }

    @ApiOperation(value = "我的已结束任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/ended", method = RequestMethod.GET)
    public Page<MessageSummary> listEndedMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listEndedMessages(queryRequest, user);
    }

    @ApiOperation(value = "我的未结束任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/notend", method = RequestMethod.GET)
    public Page<MessageSummary> listNotEndMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listNotEndMessages(queryRequest, user);
    }

    @ApiOperation(value = "我收藏的任务,支持分页,支持动态查询")
    @RequestMapping(value = "/tasks/favorite", method = RequestMethod.GET)
    public Page<MessageSummary> listFavoriteMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.listFavoriteMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的所有任务（不区分状态）, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfAll", method = RequestMethod.GET)
    public long countOfAllMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfAllMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的待处理任务, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfPending", method = RequestMethod.GET)
    public long countOfPendingMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfPendingMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我的已处理任务, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfProcessed", method = RequestMethod.GET)
    public long countOfProcessedMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfProcessedMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 已结束的任务, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfEnded", method = RequestMethod.GET)
    public long countOfEndedMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfEndedMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 未结束的任务, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfNotEnd", method = RequestMethod.GET)
    public long countOfNotEndMessages(TaskQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfNotEndMessages(queryRequest, user);
    }

    @ApiOperation(value = "数据总数合计 - 我收藏的任务, 支持动态查询")
    @RequestMapping(value = "/tasks/countOfFavorite", method = RequestMethod.GET)
    public long countOfFavoriteMessages(PageQueryParameter queryRequest) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getCountOfFavoriteMessages(queryRequest, user);
    }

    @ApiOperation(value = "查看任务（任务）明细")
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public MessageDetail getMessageDetail(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getMessageDetail(id, user);
    }

    @ApiOperation(value = "查看任务（任务）- 参与人")
    @RequestMapping(value = "/tasks/{id}/participant", method = RequestMethod.GET)
    public MessageParticipant getMessageParticipant(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return messageRestSupport.getMessageParticipant(id, user);
    }

    @ApiOperation(value = "收藏任务（重复收藏自动忽略）")
    @RequestMapping(value = "/tasks/{id}/favorite", method = RequestMethod.POST)
    public void addMessageToFavorite(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        messageRestSupport.addMessageToFavorite(id, user);
    }

    @ApiOperation(value = "取消收藏任务（重复取消自动忽略）")
    @RequestMapping(value = "/tasks/{id}/favorite", method = RequestMethod.DELETE)
    public void removeMessageFromFavorite(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        messageRestSupport.removeMessageFromFavorite(id, user);
    }

}

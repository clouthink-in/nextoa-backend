package in.clouthink.synergy.attachment.rest.controller;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.attachment.rest.view.*;
import in.clouthink.synergy.attachment.rest.param.AttachmentSearchParam;
import in.clouthink.synergy.attachment.rest.param.SaveAttachmentParam;
import in.clouthink.synergy.attachment.rest.support.AttachmentRestSupport;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.rest.UploadFileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "/api/attachments", description = "附件发布管理")
@RestController
@RequestMapping("/api/attachments")
public class AttachmentRestController {

    @Autowired
    private AttachmentRestSupport attachmentsRestSupport;

    /**
     * 头像特殊处理,上传后,对头像进行crop和zoom操作
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostAuthorize(value = "/avatar")
    @ResponseBody
    public String uploadAvatar(UploadFileRequest uploadFileRequest,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        User user = (User) SecurityContexts.getContext().requireUser();
        uploadFileRequest.setUploadedBy(user.getUsername());
        FileObject fileObject = attachmentsRestSupport.uploadAvatar(uploadFileRequest, request, response);

        return fileObject.getId();
    }

    @ApiOperation(value = "附件列表,支持分页,支持动态查询(按名称,分类查询)")
    @GetMapping()
    public Page<AttachmentView> listAttachmentSummaryPage(AttachmentSearchParam queryRequest) {
        return attachmentsRestSupport.listAttachment(queryRequest);
    }

    @ApiOperation(value = "查看附件详情")
    @GetMapping(value = "/{id}")
    public AttachmentDetailView getAttachmentDetail(@PathVariable String id) {
        return attachmentsRestSupport.getAttachmentDetail(id);
    }

    @ApiOperation(value = "创建附件（前提已经调用daas-fss上传文件得到文件的metadata,然后和业务数据放到一起）")
    @PostMapping()
    public String createAttachment(@RequestBody SaveAttachmentParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return attachmentsRestSupport.createAttachment(request, user);
    }

    @ApiOperation(value = "修改附件信息（名称,分类等）,已发布的附件不能修改")
    @PostMapping(value = "/{id}")
    public void updateNew(@PathVariable String id, @RequestBody SaveAttachmentParam request) {
        User user = (User) SecurityContexts.getContext().requireUser();
        attachmentsRestSupport.updateAttachment(id, request, user);
    }

    @ApiOperation(value = "删除附件（已发布的附件不能删除）")
    @DeleteMapping(value = "/{id}")
    public void deleteAttachment(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        attachmentsRestSupport.deleteAttachment(id, user);
    }

    @ApiOperation(value = "发布附件（重复发布自动忽略）")
    @PostMapping(value = "/{id}/publish")
    public void publishAttachment(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        attachmentsRestSupport.publishAttachment(id, user);
    }

    @ApiOperation(value = "取消发布附件（重复取消自动忽略）")
    @PostMapping(value = "/{id}/unpublish")
    public void unpublishAttachment(@PathVariable String id) {
        User user = (User) SecurityContexts.getContext().requireUser();
        attachmentsRestSupport.unpublishAttachment(id, user);
    }

    @ApiOperation(value = "下载附件")
    @GetMapping(value = {"/{id}/download"})
    public void downloadAttachment(@PathVariable String id, HttpServletResponse response) throws IOException {
        User user = (User) SecurityContexts.getContext().requireUser();
        attachmentsRestSupport.downloadAttachment(id, user, response);
    }

    @ApiOperation(value = "查看附件的下载历史记录")
    @GetMapping(value = "/{id}/download-history")
    public Page<DownloadView> listDownloadHistory(@PathVariable String id, PageSearchParam queryParameter) {
        return attachmentsRestSupport.listDownloadHistory(id, queryParameter);
    }

}

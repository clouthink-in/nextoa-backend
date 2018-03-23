package in.clouthink.synergy.attachment.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.attachment.rest.view.*;
import in.clouthink.synergy.attachment.rest.param.AttachmentSearchParam;
import in.clouthink.synergy.attachment.rest.param.SaveAttachmentParam;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.rest.UploadFileRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public interface AttachmentRestSupport {

	Page<AttachmentView> listAttachment(AttachmentSearchParam queryRequest);

	AttachmentDetailView getAttachmentDetail(String id);

	String createAttachment(SaveAttachmentParam request, User user);

	void updateAttachment(String id, SaveAttachmentParam request, User user);

	void deleteAttachment(String id, User user);

	void publishAttachment(String id, User user);

	void unpublishAttachment(String id, User user);

	Page<DownloadView> listDownloadHistory(String id, PageSearchParam queryParameter);

	void downloadAttachment(String id, User user, HttpServletResponse response) throws IOException;

	FileObject uploadAvatar(UploadFileRequest uploadFileRequest,
							HttpServletRequest request,
							HttpServletResponse response) throws IOException;
}

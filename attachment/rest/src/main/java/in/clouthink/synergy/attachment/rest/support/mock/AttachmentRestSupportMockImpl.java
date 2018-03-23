package in.clouthink.synergy.attachment.rest.support.mock;

import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.rest.UploadFileRequest;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.attachment.rest.view.*;
import in.clouthink.synergy.attachment.rest.param.AttachmentSearchParam;
import in.clouthink.synergy.attachment.rest.param.SaveAttachmentParam;
import in.clouthink.synergy.attachment.rest.support.AttachmentRestSupport;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AttachmentRestSupport mocker
 *
 * @author dz
 */
@Component
public class AttachmentRestSupportMockImpl implements AttachmentRestSupport {
	@Override
	public Page<AttachmentView> listAttachment(AttachmentSearchParam queryRequest) {
		return null;
	}

	@Override
	public AttachmentDetailView getAttachmentDetail(String id) {
		return null;
	}

	@Override
	public String createAttachment(SaveAttachmentParam request, User user) {
		return null;
	}

	@Override
	public void updateAttachment(String id, SaveAttachmentParam request, User user) {

	}

	@Override
	public void deleteAttachment(String id, User user) {

	}

	@Override
	public void publishAttachment(String id, User user) {

	}

	@Override
	public void unpublishAttachment(String id, User user) {

	}

	@Override
	public Page<DownloadView> listDownloadHistory(String id, PageSearchParam queryParameter) {
		return null;
	}

	@Override
	public void downloadAttachment(String id, User user, HttpServletResponse response) throws IOException {

	}

	@Override
	public FileObject uploadAvatar(UploadFileRequest uploadFileRequest,
								   HttpServletRequest request,
								   HttpServletResponse response) throws IOException {
		return null;
	}
}

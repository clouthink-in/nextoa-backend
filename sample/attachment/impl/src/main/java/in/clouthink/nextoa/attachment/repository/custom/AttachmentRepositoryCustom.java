package in.clouthink.nextoa.attachment.repository.custom;

import in.clouthink.nextoa.attachment.domain.model.Attachment;
import in.clouthink.nextoa.attachment.domain.request.AttachmentQueryRequest;
import org.springframework.data.domain.Page;

public interface AttachmentRepositoryCustom {

	Page<Attachment> queryPage(AttachmentQueryRequest queryRequest);

	void updateDownloadCounter(String id, int downloadCounter);

}

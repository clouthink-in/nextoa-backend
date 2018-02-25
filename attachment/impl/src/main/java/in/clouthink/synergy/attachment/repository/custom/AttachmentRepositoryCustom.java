package in.clouthink.synergy.attachment.repository.custom;

import in.clouthink.synergy.attachment.domain.model.Attachment;
import in.clouthink.synergy.attachment.domain.request.AttachmentQueryRequest;
import org.springframework.data.domain.Page;

public interface AttachmentRepositoryCustom {

	Page<Attachment> queryPage(AttachmentQueryRequest queryRequest);

	void updateDownloadCounter(String id, int downloadCounter);

}

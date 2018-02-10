package in.clouthink.nextoa.attachment.repository;

import in.clouthink.nextoa.attachment.domain.model.Attachment;
import in.clouthink.nextoa.attachment.repository.custom.AttachmentRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * the attachment persist service
 */
public interface AttachmentRepository extends AbstractRepository<Attachment>, AttachmentRepositoryCustom {

	Page<Attachment> findByPublished(boolean published, Pageable pageable);

}
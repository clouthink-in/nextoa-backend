package in.clouthink.synergy.attachment.repository;

import in.clouthink.synergy.attachment.domain.model.Attachment;
import in.clouthink.synergy.attachment.repository.custom.AttachmentRepositoryCustom;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * the attachment persist service
 */
public interface AttachmentRepository extends AbstractRepository<Attachment>, AttachmentRepositoryCustom {

	Page<Attachment> findByPublished(boolean published, Pageable pageable);

}
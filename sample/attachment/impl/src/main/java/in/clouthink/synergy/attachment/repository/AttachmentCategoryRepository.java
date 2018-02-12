package in.clouthink.synergy.attachment.repository;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.attachment.domain.model.AttachmentCategory;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * the attachment category persist service
 */
public interface AttachmentCategoryRepository extends AbstractRepository<AttachmentCategory> {

	Page<AttachmentCategory> findByCreatedBy(User createdBy, Pageable pageable);

}

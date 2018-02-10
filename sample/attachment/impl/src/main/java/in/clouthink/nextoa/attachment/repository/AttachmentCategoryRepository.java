package in.clouthink.nextoa.attachment.repository;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.attachment.domain.model.AttachmentCategory;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * the attachment category persist service
 */
public interface AttachmentCategoryRepository extends AbstractRepository<AttachmentCategory> {

	Page<AttachmentCategory> findByCreatedBy(User createdBy, Pageable pageable);

}

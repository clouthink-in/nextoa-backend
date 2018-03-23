package in.clouthink.synergy.attachment.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;

import java.util.Date;

/**
 *
 */
public interface AttachmentSearchRequest extends DateRangedSearchRequest {

	String getTitle();

	String getCategory();

	Boolean getPublished();

	Date getCreatedAtBegin();

	Date getCreatedAtEnd();

}

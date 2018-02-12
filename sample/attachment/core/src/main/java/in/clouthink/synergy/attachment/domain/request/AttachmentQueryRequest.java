package in.clouthink.synergy.attachment.domain.request;

import in.clouthink.synergy.shared.domain.request.DateRangedQueryRequest;

import java.util.Date;

/**
 *
 */
public interface AttachmentQueryRequest extends DateRangedQueryRequest {

	String getTitle();

	String getCategory();

	Boolean getPublished();

	Date getCreatedAtBegin();

	Date getCreatedAtEnd();

}

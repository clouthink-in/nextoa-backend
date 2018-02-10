package in.clouthink.nextoa.attachment.domain.request;


import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.attachment.domain.model.Attachment;

/**
 * The download attachment event
 */
public interface DownloadAttachmentEvent {

	String EVENT_NAME = DownloadAttachmentEvent.class.getName();

	Attachment getAttachment();

	User getUser();

}

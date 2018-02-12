package in.clouthink.synergy.attachment.domain.request;


import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.attachment.domain.model.Attachment;

/**
 * The download attachment event
 */
public interface DownloadAttachmentEvent {

	String EVENT_NAME = DownloadAttachmentEvent.class.getName();

	Attachment getAttachment();

	User getUser();

}

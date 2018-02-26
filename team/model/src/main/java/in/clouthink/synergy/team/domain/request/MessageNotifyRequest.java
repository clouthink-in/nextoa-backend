package in.clouthink.synergy.team.domain.request;

/**
 */
public interface MessageNotifyRequest {

	String MESSAGE_NOTIFY = "MESSAGE_NOTIFY";

	String getCellphone();

	String getMessageId();

	String getMessageSender();

	String getMessageTitle();

}

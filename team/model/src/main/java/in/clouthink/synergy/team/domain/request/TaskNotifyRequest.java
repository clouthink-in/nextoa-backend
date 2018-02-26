package in.clouthink.synergy.team.domain.request;

/**
 */
public interface TaskNotifyRequest {

	String TASK_NOTIFY = "TASK_NOTIFY";

	String getTelephone();

	String getTaskId();

	String getTaskSender();

	String getTaskTitle();

}

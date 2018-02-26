package in.clouthink.synergy.team.domain.request;

/**
 */
public class DefaultTaskNotifyRequest implements TaskNotifyRequest {

	private String telephone;

	private String taskId;

	private String taskSender;

	private String taskTitle;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public String getTaskSender() {
		return taskSender;
	}

	public void setTaskSender(String taskSender) {
		this.taskSender = taskSender;
	}

	@Override
	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
}

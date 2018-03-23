package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.Activity;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class TaskDetailView extends TaskView {

	public static TaskDetailView from(Task task, Activity activity) {
		if (task == null) {
			return null;
		}
		TaskDetailView result = new TaskDetailView();
		ActivityDetailView activityDetailView = ActivityDetailView.from(activity);
		convert(task, activity, result);
		result.setBizDetail(activityDetailView);
		return result;
	}

	//对应业务明细,例如ActivityDetail
	Object bizDetail;

	public Object getBizDetail() {
		return bizDetail;
	}

	public void setBizDetail(Object bizDetail) {
		this.bizDetail = bizDetail;
	}

}

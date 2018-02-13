package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.Activity;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class TaskDetail extends TaskSummary {

	public static TaskDetail from(Task task, Activity activity) {
		if (task == null) {
			return null;
		}
		TaskDetail result = new TaskDetail();
		ActivityDetail activityDetail = ActivityDetail.from(activity);
		convert(task, activity, result);
		result.setBizDetail(activityDetail);
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

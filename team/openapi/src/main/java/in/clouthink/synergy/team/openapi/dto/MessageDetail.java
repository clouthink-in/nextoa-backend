package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.Activity;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class MessageDetail extends MessageSummary {

	public static MessageDetail from(Task task, Activity activity) {
		if (task == null) {
			return null;
		}
		MessageDetail result = new MessageDetail();
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

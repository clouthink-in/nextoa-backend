package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.Receiver;
import in.clouthink.synergy.team.domain.request.AbstractActivityRequest;

import java.util.List;

/**
 *
 */
public class AbstractActivityRequestParameter implements AbstractActivityRequest {

	//协作请求正文
	private String activityContent;

	//主送
	private List<Receiver> toReceivers;

	//抄送
	private List<Receiver> ccReceivers;

	@Override
	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	@Override
	public List<Receiver> getToReceivers() {
		return toReceivers;
	}

	public void setToReceivers(List<Receiver> toReceivers) {
		this.toReceivers = toReceivers;
	}

	@Override
	public List<Receiver> getCcReceivers() {
		return ccReceivers;
	}

	public void setCcReceivers(List<Receiver> ccReceivers) {
		this.ccReceivers = ccReceivers;
	}

}

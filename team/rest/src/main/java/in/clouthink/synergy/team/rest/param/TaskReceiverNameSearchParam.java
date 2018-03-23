package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;

/**
 */
public class TaskReceiverNameSearchParam extends PageSearchParam {

	private String receiverName;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

}

package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.team.domain.model.ActivityActionType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class StartActivityParameter extends AbstractActivityParameter {

	//PRINT,EDIT,COPY,FORWARD
	private List<ActivityActionType> disabledActions;

	public List<ActivityActionType> getDisabledActions() {
		return disabledActions;
	}

	public void setDisabledActions(List<ActivityActionType> disabledActions) {
		this.disabledActions = disabledActions;
	}
}

package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.team.domain.model.ActivityActionType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class StartActivityParam extends AbstractActivityParam {

	//PRINT,EDIT,COPY,FORWARD
	private List<ActivityActionType> disabledActions;

	public List<ActivityActionType> getDisabledActions() {
		return disabledActions;
	}

	public void setDisabledActions(List<ActivityActionType> disabledActions) {
		this.disabledActions = disabledActions;
	}

}

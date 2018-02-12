package in.clouthink.synergy.team.domain.request;

import in.clouthink.synergy.team.domain.model.ActivityActionType;

import java.util.List;

/**
 *
 */
public interface StartActivityRequest extends AbstractActivityRequest {

    //允许的操作
    List<ActivityActionType> getDisabledActions();

}

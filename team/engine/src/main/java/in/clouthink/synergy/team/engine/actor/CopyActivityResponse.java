package in.clouthink.synergy.team.engine.actor;

import in.clouthink.synergy.team.domain.model.Activity;

/**
 * @auther dz
 */
public class CopyActivityResponse extends AbstractActivityResponse {

    private Activity activity;

    public CopyActivityResponse(Throwable throwable) {
        super(throwable);
    }

    public CopyActivityResponse(Activity activity) {
        super(null);
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}

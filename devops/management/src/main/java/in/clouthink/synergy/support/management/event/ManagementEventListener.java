package in.clouthink.synergy.support.management.event;

import in.clouthink.synergy.shared.event.GeneralApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @auther dz
 */
public class ManagementEventListener implements ApplicationListener<GeneralApplicationEvent> {

    @Override
    public void onApplicationEvent(GeneralApplicationEvent event) {
        //TODO
        //update the queued activity request & queued web request
    }

}

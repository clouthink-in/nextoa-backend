package in.clouthink.synergy.support.management.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @auther dz
 */
@ManagedResource()
public class QueuedActivityRequest {

    @Value("${synergy.queued.activity.request:1000}")
    private int threshold = 1000;

    @ManagedAttribute
    public int getThreshold() {
        return threshold;
    }

    @ManagedAttribute
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}

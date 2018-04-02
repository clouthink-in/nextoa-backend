package in.clouthink.synergy.support.throttle;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auther dz
 */
@ConfigurationProperties(prefix = "synergy.support.throttle")
public class ThrottleSupportProperties {

    private boolean queuedPageEnabled = false;

    private String queuedPage = "/waiting.html";

    private int maxRate = 100;

    private int waitTimeout = 1000;

    public boolean isQueuedPageEnabled() {
        return queuedPageEnabled;
    }

    public void setQueuedPageEnabled(boolean queuedPageEnabled) {
        this.queuedPageEnabled = queuedPageEnabled;
    }

    public String getQueuedPage() {
        return queuedPage;
    }

    public void setQueuedPage(String queuedPage) {
        this.queuedPage = queuedPage;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public int getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }
}

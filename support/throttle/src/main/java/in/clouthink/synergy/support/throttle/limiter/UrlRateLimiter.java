package in.clouthink.synergy.support.throttle.limiter;

/**
 * @auther dz
 */
public interface UrlRateLimiter {

    String[] getUrls();

    int getRate();
}

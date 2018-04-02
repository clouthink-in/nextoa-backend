package in.clouthink.synergy.support.throttle.limiter;

/**
 * @auther dz
 */
public class DefaultUrlRateLimiter implements UrlRateLimiter {

    private String[] urls;

    private int rate;

    public DefaultUrlRateLimiter(String[] urls, int rate) {
        this.urls = urls;
        this.rate = rate;
    }

    @Override
    public String[] getUrls() {
        return urls;
    }

    @Override
    public int getRate() {
        return rate;
    }

}

package in.clouthink.synergy.support.throttle.limiter;

import com.google.common.util.concurrent.RateLimiter;
import in.clouthink.synergy.support.throttle.ThrottleSupportProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @auther dz
 */
public class ThrottleFilter implements Filter, InitializingBean {

    @Autowired
    private ThrottleSupportProperties throttleSupportProperties;

    private ThrottleResponseHandler throttleResponseHandler = new DefaultThrottleResponseHandler();

    private RateLimiter rateLimiter;

    public void setThrottleResponseHandler(ThrottleResponseHandler throttleResponseHandler) {
        Assert.notNull(throttleResponseHandler, "throttleResponseHandler is required.");
        this.throttleResponseHandler = throttleResponseHandler;
    }

    public void setRate(int rate) {
        if (rate > 0) {
            rateLimiter = RateLimiter.create(rate);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (rateLimiter == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!rateLimiter.tryAcquire(throttleSupportProperties.getWaitTimeout(), TimeUnit.MILLISECONDS)) {
            if (throttleSupportProperties.isQueuedPageEnabled()) {
                request.getRequestDispatcher(throttleSupportProperties.getQueuedPage()).forward(request, response);
            }
            else {
                throttleResponseHandler.handle((HttpServletResponse) response);
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(throttleSupportProperties, "throttleSupportProperties is required.");
        if (throttleSupportProperties.getMaxRate() > 0) {
            rateLimiter = RateLimiter.create(throttleSupportProperties.getMaxRate());
        }
    }

}

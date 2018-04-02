package in.clouthink.synergy.support.throttle.limiter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther dz
 */
public class DefaultThrottleResponseHandler implements ThrottleResponseHandler {

    @Override
    public void handle(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
        response.getWriter()
                .append(String.format("{\"succeed\":false,\"message\":\"%s\"}", "extend request limit"));
        response.flushBuffer();
    }

}

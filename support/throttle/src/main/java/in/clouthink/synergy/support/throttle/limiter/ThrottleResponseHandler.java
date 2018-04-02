package in.clouthink.synergy.support.throttle.limiter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther dz
 */
public interface ThrottleResponseHandler {

    void handle(HttpServletResponse response) throws IOException;

}

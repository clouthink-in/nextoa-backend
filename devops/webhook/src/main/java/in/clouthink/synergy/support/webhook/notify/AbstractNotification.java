package in.clouthink.synergy.support.webhook.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class AbstractNotification {

    @Value("${app.name}")
    private String appName;

    @Value("${spring.application.name:synergy}")
    private String springApplicationName;

    String getAppName() {
        if (!StringUtils.isEmpty(springApplicationName)) {
            return springApplicationName;
        }
        if (!StringUtils.isEmpty(appName)) {
            return appName;
        }
        return "spring-backend-boilerplate";
    }
}

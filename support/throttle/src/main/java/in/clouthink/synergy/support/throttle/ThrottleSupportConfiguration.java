package in.clouthink.synergy.support.throttle;

import in.clouthink.synergy.support.throttle.limiter.ThrottleFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @auther dz
 */
@Configuration
@EnableConfigurationProperties(ThrottleSupportProperties.class)
public class ThrottleSupportConfiguration {

    @Bean
    public ThrottleFilter throttleFilter() {
        return new ThrottleFilter();
    }

    @Bean
    public FilterRegistrationBean httpMethodFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(throttleFilter());
        registration.addUrlPatterns("/*");
        registration.setName("throttleFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

}

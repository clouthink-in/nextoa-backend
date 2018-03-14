package in.clouthink.synergy.support.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringSessionConfiguration {

    @Bean
    public MockInitializingBean mockInitializingBean() {
        return new MockInitializingBean();
    }

}

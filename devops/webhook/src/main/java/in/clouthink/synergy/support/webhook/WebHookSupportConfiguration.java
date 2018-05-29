package in.clouthink.synergy.support.webhook;

import in.clouthink.synergy.support.webhook.client.WebHookClient;
import in.clouthink.synergy.support.webhook.client.impl.WebHookClientBearyChatImpl;
import in.clouthink.synergy.support.webhook.notify.ShutdownNotification;
import in.clouthink.synergy.support.webhook.notify.StartupNotification;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
@EnableConfigurationProperties(WebHookSupportProperties.class)
public class WebHookSupportConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebHookClient webhookClient() {
        return new WebHookClientBearyChatImpl();
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> startupNotification() {
        return new StartupNotification();
    }

    @Bean
    public ApplicationListener<ApplicationFailedEvent> shutdownNotificatioin() {
        return new ShutdownNotification();
    }

}

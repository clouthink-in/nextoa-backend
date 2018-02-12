package in.clouthink.synergy.storage;

import in.clouthink.synergy.storage.local.LocalfsDownloadUrlProvider;
import in.clouthink.synergy.storage.spi.DownloadUrlProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ConfigurationProperties.class})
public class LocalfsModuleConfiguration {

	@Bean
	public DownloadUrlProvider storageService() {
		return new LocalfsDownloadUrlProvider();
	}

}

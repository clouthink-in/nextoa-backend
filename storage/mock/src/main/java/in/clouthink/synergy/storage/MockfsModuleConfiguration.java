package in.clouthink.synergy.storage;

import in.clouthink.synergy.storage.mock.MockfsDownloadUrlProvider;
import in.clouthink.synergy.storage.spi.DownloadUrlProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockfsModuleConfiguration {

	@Bean
	public DownloadUrlProvider mockfsDownloadUrlProvider() {
		return new MockfsDownloadUrlProvider();
	}

}

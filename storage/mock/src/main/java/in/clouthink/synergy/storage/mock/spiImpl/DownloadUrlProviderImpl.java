package in.clouthink.synergy.storage.mock.spiImpl;

import in.clouthink.synergy.storage.spi.DownloadUrlProvider;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class DownloadUrlProviderImpl implements DownloadUrlProvider {

	@Override
	public String getDownloadUrl(String id) {
		return "#";
	}

}

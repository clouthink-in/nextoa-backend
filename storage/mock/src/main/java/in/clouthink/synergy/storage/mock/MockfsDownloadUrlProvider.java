package in.clouthink.synergy.storage.mock;

import in.clouthink.synergy.storage.spi.DownloadUrlProvider;

/**
 * @author dz
 */
public class MockfsDownloadUrlProvider implements DownloadUrlProvider {

	@Override
	public String getDownloadUrl(String id) {
		return "#";
	}

}

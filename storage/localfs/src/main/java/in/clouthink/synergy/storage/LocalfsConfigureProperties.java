package in.clouthink.synergy.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "synergy.storage.local")
public class LocalfsConfigureProperties {

	private String path = "/var/sbb/storage";

	private String dowloadUrlPrefix = "/static/download";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDowloadUrlPrefix() {
		return dowloadUrlPrefix;
	}

	public void setDowloadUrlPrefix(String dowloadUrlPrefix) {
		this.dowloadUrlPrefix = dowloadUrlPrefix;
	}

}

package in.clouthink.synergy.storage.alioss;

import in.clouthink.daas.fss.alioss.support.impl.DefaultOssProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "synergy.storage.alioss")
public class AliossConfigureProperties extends DefaultOssProperties {

}

package in.clouthink.synergy.support.management;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("in.clouthink.synergy.support.management")
@EnableMBeanExport
@Profile("prod")
public class JmxConfiguration {

}

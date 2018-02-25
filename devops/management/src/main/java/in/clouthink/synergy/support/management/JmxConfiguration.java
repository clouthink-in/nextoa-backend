package in.clouthink.synergy.support.management;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@ComponentScan("in.clouthink.synergy.support.management")
@EnableMBeanExport
public class JmxConfiguration {

}

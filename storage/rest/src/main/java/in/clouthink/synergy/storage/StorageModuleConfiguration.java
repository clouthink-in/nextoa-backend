package in.clouthink.synergy.storage;

import in.clouthink.synergy.storage.support.AdvancedFileObjectQueryRestSupport;
import in.clouthink.synergy.storage.support.impl.AdvancedFileObjectQueryRestSupportImpl;
import in.clouthink.synergy.storage.support.mock.AdvancedFileObjectQueryRestSupportMockImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"in.clouthink.daas.fss.rest",
        "in.clouthink.synergy.storage.rest.controller"})
public class StorageModuleConfiguration {

    @Bean
    @ConditionalOnMissingBean(AdvancedFileObjectQueryRestSupport.class)
    public AdvancedFileObjectQueryRestSupport advancedFileObjectQueryRestSupportImpl() {
        return new AdvancedFileObjectQueryRestSupportImpl();
    }

    @Bean
    @ConditionalOnBean(name = "mockfsDownloadUrlProvider")
    public AdvancedFileObjectQueryRestSupport advancedFileObjectQueryRestSupportMockImpl() {
        return new AdvancedFileObjectQueryRestSupportMockImpl();
    }

}

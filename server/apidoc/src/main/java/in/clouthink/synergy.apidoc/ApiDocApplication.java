package in.clouthink.synergy.apidoc;

import in.clouthink.synergy.account.AccountMockConfiguration;
import in.clouthink.synergy.attachment.AttachmentMockConfiguration;
import in.clouthink.synergy.audit.AuditMockConfiguration;
import in.clouthink.synergy.rbac.RbacMockConfiguration;
import in.clouthink.synergy.sms.SmsMockConfiguration;
import in.clouthink.synergy.storage.StorageMockConfiguration;
import in.clouthink.synergy.team.TeamMockConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@Import({Swagger2Configuration.class})
public class ApiDocApplication extends SpringBootServletInitializer {

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.getSources().remove(ErrorPageFilter.class);
        return super.run(application);
    }

    public static void main(String[] args) {
        SpringApplication.run(
                new Object[]{AccountMockConfiguration.class,
                        AttachmentMockConfiguration.class,
                        AuditMockConfiguration.class,
                        RbacMockConfiguration.class,
                        StorageMockConfiguration.class,
                        SmsMockConfiguration.class,
                        TeamMockConfiguration.class,
                        ApiDocApplication.class}, args);
    }

}

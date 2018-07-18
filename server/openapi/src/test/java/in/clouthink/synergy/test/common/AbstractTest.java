package in.clouthink.synergy.test.common;

import in.clouthink.synergy.test.application.OpenApiApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenApiApplication.class)
@WithMockUser(username = "administrator", roles = {"ADMIN", "USER"})
@ActiveProfiles(profiles = "test")
public abstract class AbstractTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}

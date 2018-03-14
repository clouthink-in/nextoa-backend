package in.clouthink.synergy.support.mock;

import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.RoleService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @auther dz
 */
public class MockInitializingBean implements InitializingBean {

    private AccountService accountService;

    private RoleService roleService;

    void createUsers() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createUsers();
    }

}

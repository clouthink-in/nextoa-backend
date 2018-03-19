package in.clouthink.synergy.account.initialize;

import in.clouthink.synergy.account.AdministratorAccountProperties;
import in.clouthink.synergy.account.domain.model.*;
import in.clouthink.synergy.account.rest.dto.SaveRoleParameter;
import in.clouthink.synergy.account.rest.dto.SaveUserParameter;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dz
 */
public class AccountInitializingBean implements InitializingBean {

    private static final Log logger = LogFactory.getLog(AccountInitializingBean.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdministratorAccountProperties administratorAccountProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        tryCreateBuiltinRoles();
        tryCreateAdministrator();
    }

    private void tryCreateBuiltinRoles() {
        Roles.initialize().forEach(role -> {
            Role target = roleService.findByCode(role.getCode());
            if (target != null) {
                logger.debug("The role is created before, we will skip it");
                return;
            }

            SaveRoleParameter parameter = new SaveRoleParameter();
            parameter.setCode(role.getCode());
            parameter.setName(role.getName());
            parameter.setDescription(role.getName());
            roleService.createRole(parameter, role.getType(), null);
        });
    }

    private void tryCreateAdministrator() {
        if (StringUtils.isEmpty(administratorAccountProperties.getUsername())) {
            logger.debug("The administrator user is not pre-configured, we will skip it");
            return;
        }

        // initialize System Administrator
        User adminUser = accountService.findByUsername(administratorAccountProperties.getUsername());
        if (adminUser != null) {
            logger.debug("The administrator user is created before, we will skip it");
            return;
        }

        SaveUserParameter saveSysUserParameter = new SaveUserParameter();
        saveSysUserParameter.setUsername(administratorAccountProperties.getUsername());
        saveSysUserParameter.setTelephone(administratorAccountProperties.getTelephone());
        saveSysUserParameter.setEmail(administratorAccountProperties.getEmail());
        saveSysUserParameter.setPassword(administratorAccountProperties.getPassword());
        saveSysUserParameter.setGender(Gender.MALE);
        accountService.createAccount(saveSysUserParameter,
                                     roleService.requireSysUserRole(),
                                     roleService.requireSysAdminRole());
    }

}

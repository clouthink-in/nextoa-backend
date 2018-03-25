package in.clouthink.synergy.security.impl.spring;

import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.service.AccountService;
import in.clouthink.synergy.account.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    /**
     * @param username the account name (may be in email format)
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (!StringUtils.isEmpty(username)) {
            username = username.trim().toLowerCase();
        }
        User user = accountService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("无效的用户名%s", username));
        }

        User result = new User();
        BeanUtils.copyProperties(user, result);
        //populate authorities
        result.getAuthorities()
              .addAll(roleService.listBindRoles(user)
                                 .stream()
                                 .map(role -> new SimpleGrantedAuthority(Roles.ROLE_PREFIX + role.getCode()))
                                 .collect(Collectors.toList()));

        return new UserDetails(result);
    }

}

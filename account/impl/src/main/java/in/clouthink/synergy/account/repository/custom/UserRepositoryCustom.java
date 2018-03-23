package in.clouthink.synergy.account.repository.custom;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.UserSearchRequest;
import in.clouthink.synergy.account.domain.request.UsernameSearchRequest;
import org.springframework.data.domain.Page;

/**
 * 系统用户持久层扩展
 *
 * @author dz
 */
public interface UserRepositoryCustom {

    Page<User> queryPage(UsernameSearchRequest queryRequest);

    Page<User> queryPage(Group group, UsernameSearchRequest queryRequest);

    Page<User> queryPage(Role role, UserSearchRequest queryRequest);

    Page<User> queryPage(UserSearchRequest queryRequest);

    Page<User> queryArchivedUsers(UserSearchRequest queryRequest);

}

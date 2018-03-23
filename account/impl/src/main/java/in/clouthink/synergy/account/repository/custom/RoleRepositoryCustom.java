package in.clouthink.synergy.account.repository.custom;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import org.springframework.data.domain.Page;

/**
 * 角色持久层扩展
 *
 * @author dz
 */
public interface RoleRepositoryCustom {

    Page<Role> queryPage(RoleSearchRequest queryRequest);

}

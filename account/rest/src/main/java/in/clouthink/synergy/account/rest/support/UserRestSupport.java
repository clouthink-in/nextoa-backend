package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserRestSupport {

    Page<UserSummary> listUsers(UserQueryParameter queryRequest);

    UserDetail getUserDetail(String id);

    User createUser(SaveUserParameter request, User byWho);

    void updateUser(String id, SaveUserParameter request, User byWho);

    void deleteUser(String id, User byWho);

    void changePassword(String id, ChangePasswordRequest request, User byWho);

    void enableUser(String id, User byWho);

    void disableUser(String id, User byWho);

    void lockUser(String id, User byWho);

    void unlockUser(String id, User byWho);

    List<GroupWithPath> listBindGroups(String userId);

    void bindUserAndGroups(String userId, String[] groupIds, User byWho);

    void unbindUserAndGroups(String userId, String[] groupIds, User byWho);

    List<RoleSummary> listBindRoles(String userId);

    void bindUserAndRoles(String userId, String[] roleIds, User byWho);

    void unbindUserAndRoles(String userId, String[] roleIds, User byWho);

}

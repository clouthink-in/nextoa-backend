package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserRestSupport {

    Page<UserView> listUsers(UserSearchParam queryRequest);

    UserDetailView getUserDetail(String id);

    User createUser(SaveUserParam request, User byWho);

    void updateUser(String id, SaveUserParam request, User byWho);

    void deleteUser(String id, User byWho);

    void changePassword(String id, ChangePasswordRequest request, User byWho);

    void enableUser(String id, User byWho);

    void disableUser(String id, User byWho);

    void lockUser(String id, User byWho);

    void unlockUser(String id, User byWho);

    List<GroupWithPath> listBindGroups(String userId);

    void bindUserAndGroups(String userId, String[] groupIds, User byWho);

    void unbindUserAndGroups(String userId, String[] groupIds, User byWho);

    List<RoleView> listBindRoles(String userId);

    void bindUserAndRoles(String userId, String[] roleIds, User byWho);

    void unbindUserAndRoles(String userId, String[] roleIds, User byWho);

}

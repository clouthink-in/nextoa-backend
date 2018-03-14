package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.SaveGroupRequest;
import in.clouthink.synergy.account.domain.request.SaveUserRequest;
import in.clouthink.synergy.account.domain.request.UserQueryRequest;
import in.clouthink.synergy.account.domain.request.UsernameQueryRequest;
import in.clouthink.synergy.shared.domain.request.PageQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 */
public interface GroupService {

    List<Group> listRootOrgainizations();

    List<Group> listOrgainizationChildren(String id);

    Page<User> listUsersOfGroup(String id, UsernameQueryRequest queryRequest);

    Group findGroupById(String id);

    Group createGroup(SaveGroupRequest organization, User byWho);

    void updateGroup(String id, SaveGroupRequest organization, User byWho);

    void deleteGroup(String id, User byWho);

    Group createGroupChild(String id, SaveGroupRequest request, User byWho);

    User createAppUser(String id, SaveUserRequest request, User byWho);

    void updateAppUser(String id, SaveUserRequest request, User byWho);

    void deleteAppUser(String id, User byWho);

    void updateAppUserGroupRelationship(String userId, String[] organizationIds);

}

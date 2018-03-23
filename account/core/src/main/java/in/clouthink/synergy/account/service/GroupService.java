package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.SaveGroupRequest;
import in.clouthink.synergy.account.domain.request.SaveUserRequest;
import in.clouthink.synergy.account.domain.request.UsernameSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 */
public interface GroupService {

    /**
     * @return
     */
    List<Group> listRootGroups();

    /**
     * @param groupId
     * @return
     */
    List<Group> listGroupChildren(String groupId);

    /**
     * @param groupId
     * @return
     */
    Group findGroupById(String groupId);

    /**
     * @param re
     * @param byWho
     * @return
     */
    Group createGroup(SaveGroupRequest re, User byWho);

    /**
     * @param groupId
     * @param request
     * @param byWho
     */
    void updateGroup(String groupId, SaveGroupRequest request, User byWho);

    /**
     * @param groupId
     * @param byWho
     */
    void deleteGroup(String groupId, User byWho);

    /**
     * @param groupId
     * @param request
     * @param byWho
     * @return
     */
    Group createGroupChild(String groupId, SaveGroupRequest request, User byWho);

    /**
     * Create user under group
     *
     * @param groupId
     * @param request
     * @param byWho
     * @return
     */
    User createUser(String groupId, SaveUserRequest request, User byWho);

    /**
     * @param user
     * @return the groups which the user belongs to
     */
    List<Group> listBindGroups(User user);

    /**
     * @param groupId
     * @param queryRequest
     * @return the users which the group belongs to
     */
    Page<User> listBindUsers(String groupId, UsernameSearchRequest queryRequest);

    /**
     * @param userId
     * @param groupIds
     */
    void bindUserAndGroups(String userId, String[] groupIds);

    /**
     * @param userId
     * @param groupIds
     */
    void unbindUserAndGroups(String userId, String[] groupIds);

    /**
     * @param groupId
     * @param userIds
     */
    void bindGroupAndUsers(String groupId, String[] userIds);

    /**
     * @param groupId
     * @param userIds
     */
    void unbindGroupAndUsers(String groupId, String[] userIds);

}

package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveGroupParam;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UsernameSearchParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface GroupRestSupport {

    /**
     * @return
     */
    List<GroupView> listRootGroups();

    /**
     * @param id
     * @return
     */
    List<GroupView> listGroupChildren(String id);

    /**
     * @param id
     * @param queryRequest
     * @return
     */
    Page<UserView> listBindUsers(String id, UsernameSearchParam queryRequest);

    /**
     * @param request
     * @param byWho
     * @return
     */
    String createGroup(SaveGroupParam request, User byWho);

    /**
     * @param id
     * @param request
     * @param byWho
     */
    void updateGroup(String id, SaveGroupParam request, User byWho);

    /**
     * @param id
     * @param byWho
     */
    void deleteGroup(String id, User byWho);

    /**
     * @param id
     * @param request
     * @param byWho
     * @return
     */
    String createGroupChild(String id, SaveGroupParam request, User byWho);

    /**
     * @param groupId
     * @param request
     * @param byWho
     * @return
     */
    String createUserUnderGroup(String groupId, SaveUserParam request, User byWho);

    /**
     * @param groupId
     * @param userIds
     * @param user
     */
    void bindGroupAndUsers(String groupId, String[] userIds, User user);

    /**
     * @param groupId
     * @param userIds
     * @param user
     */
    void unbindGroupAndUsers(String groupId, String[] userIds, User user);

}

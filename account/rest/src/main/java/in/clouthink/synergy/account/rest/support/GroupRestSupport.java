package in.clouthink.synergy.account.rest.support;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface GroupRestSupport {

    /**
     * @return
     */
    List<GroupSummary> listRootGroups();

    /**
     * @param id
     * @return
     */
    List<GroupSummary> listGroupChildren(String id);

    /**
     * @param id
     * @param queryRequest
     * @return
     */
    Page<UserSummary> listBindUsers(String id, UsernamePageQueryParameter queryRequest);

    /**
     * @param request
     * @param byWho
     * @return
     */
    String createGroup(SaveGroupParameter request, User byWho);

    /**
     * @param id
     * @param request
     * @param byWho
     */
    void updateGroup(String id, SaveGroupParameter request, User byWho);

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
    String createGroupChild(String id, SaveGroupParameter request, User byWho);

    /**
     * @param groupId
     * @param request
     * @param byWho
     * @return
     */
    String createUserUnderGroup(String groupId, SaveUserParameter request, User byWho);

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

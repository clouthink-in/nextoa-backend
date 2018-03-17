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
    Page<UserSummary> listUsersOfGroup(String id, UsernamePageQueryParameter queryRequest);

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
     * @param id
     * @param request
     * @param byWho
     * @return
     */
    String createAppUser(String id, SaveUserParameter request, User byWho);

    /**
     * @param userId
     * @param groupIds
     */
    void updateAppUserGroupRelationship(String userId, String[] groupIds);

    /**
     * @param userId
     * @return
     */
    List<GroupOfAppUser> getAppUserGroupRelationship(String userId);

}

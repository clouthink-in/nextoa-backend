package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.dto.*;
import in.clouthink.synergy.account.rest.support.GroupRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * GroupRestSupport Mocker
 *
 * @auther dz
 */
@Component
public class GroupRestSupportMocker implements GroupRestSupport {

    @Override
    public List<GroupSummary> listRootGroups() {
        return null;
    }

    @Override
    public List<GroupSummary> listGroupChildren(String id) {
        return null;
    }

    @Override
    public Page<UserSummary> listUsersOfGroup(String id, UsernamePageQueryParameter queryRequest) {
        return null;
    }

    @Override
    public String createGroup(SaveGroupParameter request, User byWho) {
        return null;
    }

    @Override
    public void updateGroup(String id, SaveGroupParameter request, User byWho) {

    }

    @Override
    public void deleteGroup(String id, User byWho) {

    }

    @Override
    public String createGroupChild(String id, SaveGroupParameter request, User byWho) {
        return null;
    }

    @Override
    public String createAppUser(String id, SaveUserParameter request, User byWho) {
        return null;
    }

    @Override
    public void updateAppUserGroupRelationship(String userId, String[] groupIds) {

    }

    @Override
    public List<GroupOfAppUser> getAppUserGroupRelationship(String userId) {
        return null;
    }
}

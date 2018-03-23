package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.*;
import in.clouthink.synergy.account.rest.param.SaveGroupParam;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UsernameSearchParam;
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
    public List<GroupView> listRootGroups() {
        return null;
    }

    @Override
    public List<GroupView> listGroupChildren(String id) {
        return null;
    }

    @Override
    public Page<UserView> listBindUsers(String id, UsernameSearchParam queryRequest) {
        return null;
    }

    @Override
    public String createGroup(SaveGroupParam request, User byWho) {
        return null;
    }

    @Override
    public void updateGroup(String id, SaveGroupParam request, User byWho) {

    }

    @Override
    public void deleteGroup(String id, User byWho) {

    }

    @Override
    public String createGroupChild(String id, SaveGroupParam request, User byWho) {
        return null;
    }

    @Override
    public String createUserUnderGroup(String groupId, SaveUserParam request, User byWho) {
        return null;
    }

    @Override
    public void bindGroupAndUsers(String groupId, String[] userIds, User user) {

    }

    @Override
    public void unbindGroupAndUsers(String groupId, String[] userIds, User user) {

    }
}

package in.clouthink.synergy.account.rest.support.mock;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.param.SaveUserParam;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.support.UserRestSupport;
import in.clouthink.synergy.account.rest.view.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserRestSupport mocker
 *
 * @author dz
 */
@Component
public class UserRestSupportMocker implements UserRestSupport {

	@Override
	public Page<UserView> listUsers(UserSearchParam queryRequest) {
		return null;
	}

	@Override
	public UserDetailView getUserDetail(String id) {
		return null;
	}

	@Override
	public User createUser(SaveUserParam request, User byWho) {
		return null;
	}

	@Override
	public void updateUser(String id, SaveUserParam request, User byWho) {

	}

	@Override
	public void deleteUser(String id, User byWho) {

	}

	@Override
	public void changePassword(String id, ChangePasswordRequest request, User byWho) {

	}

	@Override
	public void enableUser(String id, User byWho) {

	}

	@Override
	public void disableUser(String id, User byWho) {

	}

	@Override
	public void lockUser(String id, User byWho) {

	}

	@Override
	public void unlockUser(String id, User byWho) {

	}

	@Override
	public List<GroupWithPath> listBindGroups(String userId) {
		return null;
	}

	@Override
	public void bindUserAndGroups(String userId, String[] groupIds, User byWho) {

	}

	@Override
	public void unbindUserAndGroups(String userId, String[] groupIds, User byWho) {

	}

	@Override
	public List<RoleView> listBindRoles(String userId) {
		return null;
	}

	@Override
	public void bindUserAndRoles(String userId, String[] roleIds, User byWho) {

	}

	@Override
	public void unbindUserAndRoles(String userId, String[] roleIds, User byWho) {

	}
}

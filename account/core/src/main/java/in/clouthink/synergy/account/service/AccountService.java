package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.ChangeUserProfileRequest;
import in.clouthink.synergy.account.domain.request.SaveUserRequest;
import in.clouthink.synergy.account.domain.request.UserSearchRequest;
import org.springframework.data.domain.Page;

/**
 * The account service to do CRUD on user.
 *
 * @author dz
 */
public interface AccountService {

    /**
     * @param queryParameter
     * @return
     */
    Page<User> listUsers(UserSearchRequest queryParameter);

    /**
     * @param role
     * @param queryParameter
     * @return
     */
    Page<User> listUsersByRole(Role role, UserSearchRequest queryParameter);

    /**
     * @param queryParameter
     * @return
     */
    Page<User> listArchivedUsers(UserSearchRequest queryParameter);

    /**
     * @param userId
     * @return
     */
    User findById(String userId);

    /**
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * @param username unique username
     * @return user populated with the authorities (which will be used in the Spring Security UserDetail)
     */
    User findAccountByUsername(String username);

    /**
     * @param request
     * @param sysRoles
     * @return
     */
    User createAccount(SaveUserRequest request, Role... sysRoles);

    /**
     * @param userId
     * @param request
     * @return
     */
    User updateAccount(String userId, SaveUserRequest request);

    /**
     * @param userId
     * @param request
     * @return
     */
    User changeUserProfile(String userId, ChangeUserProfileRequest request);

    /**
     * @param userId
     * @param avatarId
     * @return
     */
    User changeUserAvatar(String userId, String avatarId, String avatarUrl);

    /**
     * @param userId
     * @return
     */
    User enable(String userId);

    /**
     * @param userId
     * @return
     */
    User disable(String userId);

    /**
     * @param userId
     * @return
     */
    User lock(String userId);

    /**
     * @param userId
     * @return
     */
    User unlock(String userId);

    /**
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    User changePassword(String userId, String oldPassword, String newPassword);

    /**
     * @param userId
     * @param newPassword
     * @return
     */
    User changePassword(String userId, String newPassword);

    /**
     * @param username
     */
    void forgetPassword(String username);

    /**
     * @param userId
     */
    void deleteUser(String userId, User byWho);

    /**
     * 归档用户,只有管理员和超级管理员能够归档用户
     *
     * @param id
     * @param byWho
     */
    void archiveAccount(String id, User byWho);

}

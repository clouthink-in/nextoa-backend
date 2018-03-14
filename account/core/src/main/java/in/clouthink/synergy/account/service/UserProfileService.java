package in.clouthink.synergy.account.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.domain.request.ChangeUserProfileRequest;

/**
 * The profile service for user to get the user's profile detail and update the user's profile
 *
 * @author dz
 */
public interface UserProfileService {

    /**
     * @param userId
     * @return
     */
    User findUserById(String userId);

    /**
     * @param userId
     * @param request
     * @return
     */
    User changeUserProfile(String userId, ChangeUserProfileRequest request);

    /**
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    User changeUserPassword(String userId, String oldPassword, String newPassword);

    /**
     * @param id
     * @param url
     * @param byWho
     */
    void updateUserAvatar(String id, String url, User byWho);

}

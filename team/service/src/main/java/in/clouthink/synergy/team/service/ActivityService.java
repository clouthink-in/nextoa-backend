package in.clouthink.synergy.team.service;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The activity service to update the data
 *
 * @author dz
 */
public interface ActivityService extends ActivityQueryService {

    /**
     * @param request
     * @param user
     * @return
     */
    Activity createActivity(SaveActivityRequest request, User user);

    /**
     * @param id
     * @param user
     * @return
     */
    Activity copyActivity(String id, User user);

    /**
     * @param id
     * @param request
     * @param user
     */
    void updateActivity(String id, SaveActivityRequest request, User user);

    /**
     * @param id
     * @param user
     */
    void deleteActivity(String id, User user);

    /**
     * @param id
     * @param user
     */
    void printActivity(String id, User user);

    /**
     * @param id
     * @param user
     */
    void revokeActivity(String id, User user);

    /**
     * @param id
     * @param request
     * @param user
     */
    void startActivity(String id, StartActivityRequest request, User user);

    /**
     * @param id
     * @param request
     * @param user
     */
    void replyActivity(String id, ReplyActivityRequest request, User user);

    /**
     * @param id
     * @param request
     * @param user
     */
    void forwardActivity(String id, ForwardActivityRequest request, User user);

    /**
     * @param id
     * @param user
     */
    void endActivity(String id, User user);

    /**
     * @param id
     * @param user
     */
    void terminateActivity(String id, User user);

    /**
     * @param id
     * @param user
     */
    void markActivityAsRead(String id, User user);
}

package in.clouthink.synergy.account.domain.request;

import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;

public interface RoleSearchRequest extends PageSearchRequest {

    /**
     * @return the role code to filter
     */
    String getCode();

    /**
     * @return the role type to filter
     */
    RoleType getType();

}

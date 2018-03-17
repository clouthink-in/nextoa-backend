package in.clouthink.synergy.account.domain.request;

import in.clouthink.synergy.shared.domain.request.PageQueryRequest;

public interface RoleQueryRequest extends PageQueryRequest {

    String getCode();
}

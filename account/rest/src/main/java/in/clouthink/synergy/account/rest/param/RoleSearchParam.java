package in.clouthink.synergy.account.rest.param;

import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.PageSearchParam;
import io.swagger.annotations.ApiModel;

@ApiModel("角色查询参数")
public class RoleSearchParam extends PageSearchParam implements RoleSearchRequest {

    private String code;

    private RoleType type;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}

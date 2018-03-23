package in.clouthink.synergy.account.rest.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.clouthink.synergy.account.domain.request.SaveRoleRequest;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("保存角色申请")
public class SaveRoleParam implements SaveRoleRequest {

    @NotNull(message = "编码不能为空")
    private String code;

    @NotNull(message = "名称不能为空")
    private String name;

    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringIdentifier.trim(name);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = StringIdentifier.trim(code);
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

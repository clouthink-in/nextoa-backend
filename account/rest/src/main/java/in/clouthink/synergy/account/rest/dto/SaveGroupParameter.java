package in.clouthink.synergy.account.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.clouthink.synergy.account.domain.request.SaveGroupRequest;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import io.swagger.annotations.ApiModel;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("保存组信息申请")
public class SaveGroupParameter implements SaveGroupRequest {

    private String code;

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

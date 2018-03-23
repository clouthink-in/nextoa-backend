package in.clouthink.synergy.account.rest.view;

import in.clouthink.synergy.account.domain.model.RoleType;
import in.clouthink.synergy.account.domain.model.Role;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;

@ApiModel("角色摘要信息")
public class RoleView {

    public static RoleView from(Role role) {
        if (role == null) {
            return null;
        }

        RoleView result = new RoleView();
        BeanUtils.copyProperties(role, result);
        return result;
    }

    private String id;

    private String code;

    private String name;

    private RoleType type;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}

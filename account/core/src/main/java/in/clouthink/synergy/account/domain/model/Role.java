package in.clouthink.synergy.account.domain.model;

import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色定义,由系统（内置）角色和应用（用户自定义）角色共同组成
 */
@Document(collection = "Roles")
public class Role extends StringIdentifier implements GrantedAuthority {

    @Indexed(unique = true)
    private String code;

    @Indexed(unique = true)
    private String name;

    private RoleType type;

    private String description;

    private Date createdAt;

    private Date modifiedAt;

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

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String getAuthority() {
        return getCode();
    }

}


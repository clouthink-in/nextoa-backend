package in.clouthink.synergy.rbac.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The Resource default impl.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultResource implements MutableResourceChild, Serializable {

    private String parentCode;

    private String type;

    private String code;

    private String name;

    private Set<Permission> permissions = new HashSet<>();

    private Map<String, Object> extraAttrs = new HashMap<>();

    @Override
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Map<String, Object> getExtraAttrs() {
        return extraAttrs;
    }

    @Override
    public void setExtraAttrs(Map<String, Object> extraAttrs) {
        this.extraAttrs = extraAttrs;
    }

    @Override
    public boolean isAllowed(String api, Action action) {
        for (Permission permission : this.getPermissions()) {
            if (permission.isAllowed(api, action)) {
                return true;
            }
        }
        return false;
    }

}

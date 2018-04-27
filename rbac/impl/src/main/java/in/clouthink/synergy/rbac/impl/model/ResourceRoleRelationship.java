package in.clouthink.synergy.rbac.impl.model;

import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document(collection = "ResourceRoleRelationships")
public class ResourceRoleRelationship extends StringIdentifier {

    @Indexed
    private String resourceCode;

    //authority code
    @Indexed
    private String roleCode;

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}

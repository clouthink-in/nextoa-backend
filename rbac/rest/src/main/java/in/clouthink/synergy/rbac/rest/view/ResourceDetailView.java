package in.clouthink.synergy.rbac.rest.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.clouthink.synergy.rbac.model.DefaultPermission;
import in.clouthink.synergy.rbac.model.Permission;
import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author dz
 */
public class ResourceDetailView extends ResourceView {

    private static void convert(Resource resource, ResourceDetailView target) {
        BeanUtils.copyProperties(resource, target);
    }

    public static ResourceDetailView from(Resource resource) {
        if (resource == null) {
            return null;
        }
        ResourceDetailView result = new ResourceDetailView();
        convert(resource, result);
        return result;
    }

    private Set<Permission> permissions = new HashSet<>();

    private List<ResourceView> children = new ArrayList<>();

    @JsonDeserialize(contentAs = DefaultPermission.class)
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    @JsonDeserialize(contentAs = PrivilegedResourceTreeView.class)
    public List<ResourceView> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceView> children) {
        this.children = children;
    }

}

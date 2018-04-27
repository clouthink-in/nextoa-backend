package in.clouthink.synergy.rbac.rest.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * privileged resource
 */
public class PrivilegedResourceDetailView extends PrivilegedResourceView {

    public static PrivilegedResourceTreeView from(Resource resource) {
        PrivilegedResourceTreeView result = new PrivilegedResourceTreeView();
        BeanUtils.copyProperties(resource, result);
        return result;
    }

    private List<PrivilegedResourceTreeView> children = new ArrayList<>();

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    @JsonDeserialize(contentAs = PrivilegedResourceTreeView.class)
    public List<PrivilegedResourceTreeView> getChildren() {
        return children;
    }

    public void setChildren(List<PrivilegedResourceTreeView> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString() + "/DefaultResourceWithChildren{" +
                "children=" + children +
                '}';
    }

}

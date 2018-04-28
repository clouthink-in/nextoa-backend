package in.clouthink.synergy.rbac.rest.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * privileged resource tree
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivilegedResourceTreeView extends PrivilegedResourceView {

    public static PrivilegedResourceTreeView from(ResourceTreeView resource) {
        PrivilegedResourceTreeView result = new PrivilegedResourceTreeView();

        BeanUtils.copyProperties(resource, result, "children");

        if (resource.getChildren() == null || resource.getChildren().isEmpty()) {
            return result;
        }

        result.setChildren(resource.getChildren()
                                   .stream()
                                   .map(PrivilegedResourceTreeView::from)
                                   .collect(Collectors.toList()));

        return result;
    }

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

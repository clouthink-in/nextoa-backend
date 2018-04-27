package in.clouthink.synergy.rbac.rest.view;

import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dz
 */
public class ResourceTreeView extends ResourceView {

    private static void convert(Resource resource, ResourceTreeView target) {
        BeanUtils.copyProperties(resource, target);
    }

    public static ResourceTreeView from(Resource resource) {
        ResourceTreeView result = new ResourceTreeView();
        convert(resource, result);
        return result;
    }

    private List<ResourceTreeView> children = new ArrayList<>();

    public List<ResourceTreeView> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeView> children) {
        this.children = children;
    }
}

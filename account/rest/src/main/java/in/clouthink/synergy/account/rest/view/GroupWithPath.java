package in.clouthink.synergy.account.rest.view;

import in.clouthink.synergy.account.domain.model.Group;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApiModel
public class GroupWithPath {

    public static void convert(Group group, GroupWithPath result) {
        result.setId(group.getId());
        result.setName(group.getName());
        result.setParentIds(new String[0]);
        if (group.getParent() != null) {
            result.setParentId(group.getParent().getId());
            result.setParentName(group.getParent().getName());
            List<String> parentIds = new ArrayList<>();
            iterateGroup(group.getParent(), parentIds);
            Collections.reverse(parentIds);
            result.setParentIds(parentIds.toArray(new String[0]));
        }
    }

    private static void iterateGroup(Group group, List<String> ids) {
        ids.add(group.getId());
        if (group.getParent() != null) {
            iterateGroup(group.getParent(), ids);
        }
    }

    public static GroupWithPath from(Group group) {
        if (group == null) {
            return null;
        }
        GroupWithPath result = new GroupWithPath();
        convert(group, result);
        return result;
    }

    private String id;
    private String name;
    private String parentId;
    private String parentName;
    private String[] parentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String[] getParentIds() {
        return parentIds;
    }

    public void setParentIds(String[] parentIds) {
        this.parentIds = parentIds;
    }
}

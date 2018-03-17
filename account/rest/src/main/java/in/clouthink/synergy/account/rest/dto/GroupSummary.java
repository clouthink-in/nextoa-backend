package in.clouthink.synergy.account.rest.dto;

import in.clouthink.synergy.account.domain.model.Group;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class GroupSummary {

    static void convert(Group group, GroupSummary result) {
        result.setId(group.getId());
        result.setCode(group.getCode());
        result.setName(group.getName());
        result.setDescription(group.getDescription());
        result.setLeaf(group.isLeaf());
        if (group.getParent() != null) {
            result.setParentId(group.getParent().getId());
            result.setParentName(group.getParent().getName());
        }
    }

    public static GroupSummary from(Group organization) {
        if (organization == null) {
            return null;
        }
        GroupSummary result = new GroupSummary();
        convert(organization, result);
        return result;
    }

    private String id;

    private String code;

    private String name;

    private String description;

    private String parentId;

    private String parentName;

    private boolean leaf;

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

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}

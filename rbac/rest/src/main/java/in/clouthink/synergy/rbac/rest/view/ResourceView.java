package in.clouthink.synergy.rbac.rest.view;

import in.clouthink.synergy.rbac.model.Resource;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dz
 */
public class ResourceView {

    private static void convert(Resource resource, ResourceView target) {
        BeanUtils.copyProperties(resource, target);
    }

    public static ResourceView from(Resource resource) {
        ResourceView result = new ResourceView();
        convert(resource, result);
        return result;
    }

    private String parentCode;

    private String code;

    private String name;

    private String type;

    private Map<String, Object> extraAttrs = new HashMap<>();

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getExtraAttrs() {
        return extraAttrs;
    }

    public void setExtraAttrs(Map<String, Object> extraAttrs) {
        this.extraAttrs = extraAttrs;
    }

}

package in.clouthink.synergy.rbac.rest.view;

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
public class PrivilegedResourceView {

    static void convert(Resource resource, PrivilegedResourceView target, Predicate<Resource> predicate) {
        BeanUtils.copyProperties(resource, target);
    }

    public static PrivilegedResourceView from(Resource resource) {
        //always convert by default
        return from(resource, res -> true);
    }


    public static PrivilegedResourceView from(ResourceView fromObj) {
        if (fromObj == null) {
            return null;
        }
        PrivilegedResourceView result = new PrivilegedResourceView();
        BeanUtils.copyProperties(fromObj, result);
        return result;
    }

    public static PrivilegedResourceView from(Resource resource, Predicate<Resource> predicate) {
        if (resource == null) {
            return null;
        }
        if (!predicate.test(resource)) {
            return null;
        }
        PrivilegedResourceView result = new PrivilegedResourceView();
        convert(resource, result, predicate);
        return result;
    }

    private boolean granted = false;

    private String code;

    private String name;

    private String type;

    private Map<String, Object> metadata = new HashMap<>();

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
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

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

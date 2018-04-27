package in.clouthink.synergy.rbac.model;

import java.util.Map;
import java.util.Set;

/**
 * @author dz
 */
public interface MutableResource extends Resource {

    void setCode(String code);

    void setName(String name);

    void setPermissions(Set<Permission> permissions);

    void setExtraAttrs(Map<String, Object> extraAttrs);

}

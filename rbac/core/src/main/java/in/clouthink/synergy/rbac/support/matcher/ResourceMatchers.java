package in.clouthink.synergy.rbac.support.matcher;

import in.clouthink.synergy.rbac.model.Action;
import in.clouthink.synergy.rbac.model.ResourceMatcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

/**
 * The ResourceMatcher utilities.
 *
 * @author dz
 */
public abstract class ResourceMatchers {

    private static final Log logger = LogFactory.getLog(ResourceMatchers.class);

    private static PathMatcher matcher = new AntPathMatcher();

    public final static ResourceMatcher matchAntPath(String httpMethod, String requestUrl) {
        return (resource) -> {
            if (resource.getPermissions() == null) {
                return false;
            }
            if (StringUtils.isEmpty(requestUrl)) {
                logger.warn("The incoming expression is empty");
                return false;
            }
            return resource.isAllowed(requestUrl, Action.valueOf(httpMethod));
        };
    }

    public final static ResourceMatcher matchType(String type) {
        return (resource) -> resource.getType().equals(type);
    }

    public final static ResourceMatcher matchCode(String code) {
        return (resource) -> resource.getCode().equals(code);
    }

}

package in.clouthink.synergy.rbac.model;

import com.google.common.collect.Sets;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.Serializable;

/**
 * @author dz
 */
public class DefaultPermission implements Permission, Serializable {

    static PathMatcher matcher = new AntPathMatcher();

    private String api;

    private Action[] actions;

    public DefaultPermission() {
    }

    public DefaultPermission(String api, Action[] actions) {
        this.api = api;
        this.actions = actions;
    }

    @Override
    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        assert api != null;
        this.api = api;
    }

    @Override
    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        assert actions != null;
        this.actions = actions;
    }

    @Override
    public boolean isAllowed(String api, Action action) {
        return (matcher.match(this.api, api) && Sets.newHashSet(this.actions).contains(action));
    }

}

package in.clouthink.synergy.rbac.model;

/**
 * @author dz
 */
public interface ResourceMatcher {

	boolean matched(Resource resource);

}

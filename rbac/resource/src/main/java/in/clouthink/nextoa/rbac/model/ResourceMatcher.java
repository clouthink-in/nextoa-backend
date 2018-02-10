package in.clouthink.nextoa.rbac.model;

/**
 * @author dz
 */
public interface ResourceMatcher {

	boolean matched(Resource resource);

}

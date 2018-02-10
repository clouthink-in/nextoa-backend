package in.clouthink.nextoa.rbac.model;

/**
 * @author dz
 */
public interface MutableResourceChild extends MutableResource, ResourceChild {

	void setParentCode(String parentCode);

}

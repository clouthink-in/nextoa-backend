package in.clouthink.nextoa.rbac.support.parser;

/**
 * The simple role parser , just take and return the input role code.
 */
public class SimpleRoleParser implements RoleParser<String> {

	@Override
	public String parse(String roleCode) {
		return roleCode;
	}

}

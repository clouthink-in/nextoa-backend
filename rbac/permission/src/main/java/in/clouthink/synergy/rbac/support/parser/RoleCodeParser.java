package in.clouthink.synergy.rbac.support.parser;

import in.clouthink.synergy.rbac.exception.PermissionException;
import in.clouthink.synergy.rbac.model.TypedCode;

/**
 * Support format : 'the-role-type:the-actual-role-code'.
 */
public class RoleCodeParser implements RoleParser<TypedCode> {

	@Override
	public TypedCode parse(String roleCode) {
		String[] splittedRoleCode = roleCode.split(":");
		if (splittedRoleCode.length != 2) {
			throw new PermissionException("无效的角色编码表达式");
		}

		return new TypedCode(splittedRoleCode[0], splittedRoleCode[1]);
	}

}


package in.clouthink.nextoa.account.rest.dto;

import in.clouthink.nextoa.account.domain.request.RoleQueryRequest;
import in.clouthink.nextoa.shared.domain.request.impl.PageQueryParameter;
import io.swagger.annotations.ApiModel;

@ApiModel("角色查询参数")
public class RoleQueryParameter extends PageQueryParameter implements RoleQueryRequest {
}

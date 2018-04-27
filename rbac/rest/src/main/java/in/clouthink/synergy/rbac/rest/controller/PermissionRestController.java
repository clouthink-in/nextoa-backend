package in.clouthink.synergy.rbac.rest.controller;

import in.clouthink.synergy.rbac.impl.model.TypedRole;
import in.clouthink.synergy.rbac.rest.support.PermissionRestSupport;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceTreeView;
import in.clouthink.synergy.rbac.rest.view.PrivilegedResourceView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/permissions", description = "授权管理")
@RestController
@RequestMapping("/api/permissions")
public class PermissionRestController {

    @Autowired
    private PermissionRestSupport permissionRestSupport;

    @ApiOperation(value = "获取指定角色的授权资源(完整资源树,通过授权标记位区分是否已授权)")
    @GetMapping(value = "/roles/{roleCode}/resources/tree")
    public List<PrivilegedResourceTreeView> listGrantedHierarchyResources(@PathVariable String roleCode) {
        return permissionRestSupport.listGrantedHierarchyResources(roleCode);
    }

    @ApiOperation(value = "获取指定角色的授权资源(完整资源列表,通过授权标记位区分是否已授权)")
    @GetMapping(value = "/roles/{roleCode}/resources/list")
    public List<PrivilegedResourceView> listGrantedFlattenResources(@PathVariable String roleCode) {
        return permissionRestSupport.listGrantedFlattenResources(roleCode);
    }

    @ApiOperation(value = "获取授权的角色列表")
    @GetMapping(value = "/resources/{resourceCode}/roles")
    public List<TypedRole> listGrantedRoles(@PathVariable String resourceCode) {
        return permissionRestSupport.listGrantedRoles(resourceCode);
    }

    @ApiOperation(value = "给指定的资源授权")
    @PostMapping(value = "/roles/{roleCode}/resources/{resourceCode}")
    public void grantResourcesToRoles(@PathVariable String roleCode,
                                      @PathVariable String resourceCode) {
        permissionRestSupport.grantResourcesToRole(roleCode, resourceCode);
    }

    @ApiOperation(value = "取消指定的资源授权")
    @DeleteMapping(value = "/roles/{roleCode}/resources/{resourceCode}")
    public void revokeResourcesFromRoles(@PathVariable String roleCode,
                                         @PathVariable String resourceCode) {
        permissionRestSupport.revokeResourcesFromRole(roleCode, resourceCode);
    }

}

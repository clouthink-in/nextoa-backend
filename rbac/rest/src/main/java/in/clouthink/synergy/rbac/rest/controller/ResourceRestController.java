package in.clouthink.synergy.rbac.rest.controller;

import in.clouthink.synergy.rbac.rest.view.*;
import in.clouthink.synergy.rbac.rest.support.ResourceRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "/resources", description = "资源列表")
@RestController
@RequestMapping("/api/resources")
public class ResourceRestController {

    @Autowired
    private ResourceRestSupport resourceRestSupport;

    @ApiOperation(value = "获取资源列表")
    @GetMapping(value = "/list")
    public List<ResourceView> listFlattenResources() {
        return resourceRestSupport.listFlattenResources();
    }

    @ApiOperation(value = "获取资源列表")
    @GetMapping(value = "/tree")
    public List<ResourceTreeView> listHierarchyResources() {
        return resourceRestSupport.listHierarchyResources();
    }

    @ApiOperation(value = "获取资源详情")
    @GetMapping(value = "/{code}")
    public ResourceDetailView getResourceDetailView(@PathVariable String code) {
        return resourceRestSupport.getResourceDetailView(code);
    }

}

package in.clouthink.synergy.attachment;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Permission;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import in.clouthink.synergy.rbac.model.Action;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource({
        @Resource(
                code = "attachment:manage",
                name = "下载管理",
                permission = {@Permission(api = "/attachments**", action = {Action.GET})}
        ),

        @Resource(
                parent = "attachment:manage",
                code = "attachment:edit",
                name = "编辑",
                permission = {@Permission(api = "/attachments**", action = {Action.POST, Action.PUT})}
        ),

        @Resource(
                parent = "attachment:manage",
                code = "attachment:delete",
                name = "删除",
                permission = {@Permission(api = "/attachments**", action = {Action.DELETE})}
        ),

        @Resource(
                parent = "attachment:manage",
                code = "attachment:publish",
                name = "发布",
                permission = {@Permission(api = "/attachments/**/publish", action = {Action.POST})}
        ),

        @Resource(
                parent = "attachment:manage",
                code = "attachment:unpublish",
                name = "取消发布",
                permission = {@Permission(api = "/attachments/**/publish", action = {Action.POST})}
        ),

})
public class AttachmentResourceConfiguration {
}

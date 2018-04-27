package in.clouthink.synergy.attachment;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        resource = {@Resource(code = "resource:dashboard:attachment",
                name = "下载管理",
//						  patterns = {"/api/attachments**", "/api/attachments/**"},
//						  actions = {@Action(code = "retrieve", name = "查看"),
//									 @Action(code = "create", name = "新增"),
//									 @Action(code = "update", name = "修改"),
//									 @Action(code = "delete", name = "删除"),
//									 @Action(code = "publish", name = "发布"),
//									 @Action(code = "unpublish", name = "取消发布")},
                metadata = {@Metadata(key = "state", value = "dashboard.attachment.list")})})
public class AttachmentMenuConfiguration {
}

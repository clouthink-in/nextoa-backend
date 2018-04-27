package in.clouthink.synergy.team;

import in.clouthink.synergy.rbac.annotation.EnableResource;
import in.clouthink.synergy.rbac.annotation.Resource;
import in.clouthink.synergy.rbac.annotation.Metadata;
import org.springframework.context.annotation.Configuration;


/**
 * @author dz
 */
@Configuration
@EnableResource(
        value = {
                @Resource(code = "value:dashboard:team:activity",
                        name = "协作管理",
//                        patterns = {"/api/activities**", "/api/activities/**"},
//                        actions = {@Action(code = "retrieve", name = "查看")},
                        metadata = {@Metadata(key = "state", value = "dashboard.team.activity.list")}),

                @Resource(code = "value:dashboard:team:task",
                        name = "任务管理",
//                        patterns = {"/api/tasks**", "/api/tasks/**"},
//                        actions = {@Action(code = "retrieve", name = "查看")},
                        metadata = {@Metadata(key = "state", value = "dashboard.team.task.list")}),
        })
public class TeamResourceConfiguration {

}

package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.team.domain.model.ActivityType;
import in.clouthink.synergy.team.domain.request.SaveActivityRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveActivityParam implements SaveActivityRequest {

    private String title;

    private String category;

    private String content;

    private Boolean urgent = Boolean.FALSE;

    private ActivityType type;

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    @Override
    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}

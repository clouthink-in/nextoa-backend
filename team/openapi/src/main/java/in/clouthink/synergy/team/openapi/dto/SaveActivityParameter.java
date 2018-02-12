package in.clouthink.synergy.team.openapi.dto;

import in.clouthink.synergy.team.domain.model.ActivityType;
import in.clouthink.synergy.team.domain.request.SaveActivityRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveActivityParameter implements SaveActivityRequest {
    
    private String title;
    
    private String category;
    
    private String content;
    
    private Boolean isUrgent;
    
    private ActivityType type;
    
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
    
    public void setType(ActivityType type) {
        this.type = type;
    }
    
    @Override
    public ActivityType getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Boolean getUrgent() {
        return isUrgent;
    }
    
    public void setUrgent(Boolean urgent) {
        isUrgent = urgent;
    }
    
}

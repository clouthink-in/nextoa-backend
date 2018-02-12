package in.clouthink.synergy.team.domain.request;

import in.clouthink.synergy.team.domain.model.ActivityType;

/**
 *
 */
public interface SaveActivityRequest {
    
    String getCategory();
    
    String getTitle();
    
    String getContent();
    
    ActivityType getType();
    
    Boolean getUrgent();
    
}

package in.clouthink.synergy.team.openapi.dto;

import java.util.List;

/**
 */
public abstract class AbstractActivityParameter {
    
    private List<ReceiverParameter> to;
    
    private List<ReceiverParameter> cc;
    
    private String activityContent;
    
    public List<ReceiverParameter> getTo() {
        return to;
    }
    
    public void setTo(List<ReceiverParameter> to) {
        this.to = to;
    }
    
    public List<ReceiverParameter> getCc() {
        return cc;
    }
    
    public void setCc(List<ReceiverParameter> cc) {
        this.cc = cc;
    }
    
    public String getActivityContent() {
        return activityContent;
    }
    
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }
}

package in.clouthink.synergy.team.rest.param;

import java.util.List;

/**
 */
public abstract class AbstractActivityParam {
    
    private List<ReceiverParam> to;
    
    private List<ReceiverParam> cc;
    
    private String activityContent;
    
    public List<ReceiverParam> getTo() {
        return to;
    }
    
    public void setTo(List<ReceiverParam> to) {
        this.to = to;
    }
    
    public List<ReceiverParam> getCc() {
        return cc;
    }
    
    public void setCc(List<ReceiverParam> cc) {
        this.cc = cc;
    }
    
    public String getActivityContent() {
        return activityContent;
    }
    
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }
}

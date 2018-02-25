package in.clouthink.synergy.shared.event;

import org.springframework.context.ApplicationEvent;

/**
 * @auther dz
 */
public class GeneralApplicationEvent extends ApplicationEvent {

    private String category;

    private Object value;

    public GeneralApplicationEvent(String category, Object value) {
        super(category);
        this.category = category;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public Object getValue() {
        return value;
    }

}

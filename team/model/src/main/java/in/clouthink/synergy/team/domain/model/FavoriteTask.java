package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 */
@Document(collection = "FavoriteTasks")
public class FavoriteTask extends StringIdentifier {

    @Indexed
    @DBRef(lazy = true)
    private Task task;

    @Indexed
    @DBRef(lazy = true)
    private User createdBy;

    private Date createdAt;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

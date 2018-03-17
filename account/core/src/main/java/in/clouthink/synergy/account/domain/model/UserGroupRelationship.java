package in.clouthink.synergy.account.domain.model;

import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Holding the relationship between user and group.  One-to-many or many-to-one are supported.
 *
 * @author dz
 */
@Document(collection = "UserGroupRelationships")
public class UserGroupRelationship extends StringIdentifier {

    @Indexed
    @DBRef
    private User user;

    @Indexed
    @DBRef
    private Group group;

    private Date createdAt;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

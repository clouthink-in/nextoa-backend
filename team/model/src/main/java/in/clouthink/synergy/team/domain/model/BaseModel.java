package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

public abstract class BaseModel extends StringIdentifier {

	public static void onCreate(BaseModel target, User byWho) {
		target.setCreatedAt(new Date());
		target.setCreatedBy(byWho);
		target.setModifiedAt(new Date());
		target.setModifiedBy(byWho);
	}

	public static void onModify(BaseModel target, User byWho) {
		target.setModifiedAt(new Date());
		target.setModifiedBy(byWho);
	}

	@Indexed
	@DBRef(lazy = true)
	private User createdBy;

	@Indexed
	private Date createdAt;

	@DBRef(lazy = true)
	private User modifiedBy;

	@Indexed
	private Date modifiedAt;

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

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}

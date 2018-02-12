package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

public abstract class StatefulModel extends BaseModel {

	public static boolean isDraft(StatefulModel target) {
		return target.getStatus() == ModelStatus.DRAFT;
	}

	public static boolean isStarted(StatefulModel target) {
		return target.getStatus() == ModelStatus.IN_PROGRESS;
	}

	public static boolean isFinished(StatefulModel target) {
		return target.getStatus() == ModelStatus.FINISH;
	}

	public static void onStart(StatefulModel target, User byWho) {
		target.setStartAt(new Date());
		target.setStartBy(byWho);
	}

	public static void onEnd(StatefulModel target, User byWho) {
		target.setEndAt(new Date());
		target.setEndBy(byWho);
	}

	@Indexed
	private ModelStatus status;

	@Indexed
	@DBRef(lazy = true)
	private User startBy;

	private Date startAt;

	@Indexed
	@DBRef(lazy = true)
	private User endBy;

	private Date endAt;

	public ModelStatus getStatus() {
		return status;
	}

	public void setStatus(ModelStatus status) {
		this.status = status;
	}

	public User getStartBy() {
		return startBy;
	}

	public void setStartBy(User startBy) {
		this.startBy = startBy;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public User getEndBy() {
		return endBy;
	}

	public void setEndBy(User endBy) {
		this.endBy = endBy;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

}

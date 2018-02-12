package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import com.google.common.hash.Hashing;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * 协作请求
 */
@Document(collection = "Activities")
public class Activity extends StringIdentifier {

	public static final String crc32(String content) {
		return Hashing.crc32().hashString(content, Charset.forName("UTF-8")).toString();
	}

	public static final boolean isEditAllowed(Activity activity) {
		if (activity == null) {
			return false;
		}
		if (activity.getAllowedActions() == null || activity.getAllowedActions().isEmpty()) {
			return false;
		}
		return activity.getAllowedActions().contains(ActivityActionType.EDIT);
	}

	public static final boolean isPrintAllowed(Activity activity) {
		if (activity == null) {
			return false;
		}
		if (activity.getAllowedActions() == null || activity.getAllowedActions().isEmpty()) {
			return false;
		}
		return activity.getAllowedActions().contains(ActivityActionType.PRINT);
	}

	public static final boolean isForwardAllowed(Activity activity) {
		if (activity == null) {
			return false;
		}
		if (activity.getAllowedActions() == null || activity.getAllowedActions().isEmpty()) {
			return false;
		}
		return activity.getAllowedActions().contains(ActivityActionType.FORWARD);
	}

	public static final boolean isCopyAllowed(Activity activity) {
		if (activity == null) {
			return false;
		}
		if (activity.getAllowedActions() == null || activity.getAllowedActions().isEmpty()) {
			return false;
		}
		return activity.getAllowedActions().contains(ActivityActionType.COPY);
	}

	@Indexed
	private String title;

	@Indexed
	private ActivityType type;

	@Indexed
	private String category;

	private boolean urgent;

	private String content;

	private String contentCrc32;

	@Indexed
	private ActivityStatus status;

	private List<ActivityActionType> allowedActions;

	// 第一动作: 发起人提交,让协作请求进入流转
	@DBRef
	private ActivityAction startActivityAction;

	// 最近一个处理人的动作: 其他人回复或转发,自己撤回
	// 如果为空,则取startActivityAction
	@DBRef
	private ActivityAction latestActivityAction;

	private int readCounter;

	@Indexed
	@DBRef
	private User createdBy;

	// 创建时间,对应草稿状态
	private Date createdAt;

	// 创建时间,对应草稿状态
	private Date modifiedAt;

	// 流转时间,对应流转状态
	private Date startedAt;

	// 撤回时间,对应撤回状态
	private Date revokedAt;

	private int version = 0;

	private boolean businessComplete = false;

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

    public void setContent(String content) {
        if (content != null) {
            this.content = content;
            this.contentCrc32 = Activity.crc32(content);
        }
    }

	public String getContentCrc32() {
		return contentCrc32;
	}

	public void setContentCrc32(String contentCrc32) {
		this.contentCrc32 = contentCrc32;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

	public List<ActivityActionType> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<ActivityActionType> allowedActions) {
		this.allowedActions = allowedActions;
	}

	public ActivityAction getStartActivityAction() {
		return startActivityAction;
	}

	public void setStartActivityAction(ActivityAction startActivityAction) {
		this.startActivityAction = startActivityAction;
	}

	public ActivityAction getLatestActivityAction() {
		return latestActivityAction;
	}

	public void setLatestActivityAction(ActivityAction latestActivityAction) {
		this.latestActivityAction = latestActivityAction;
	}

	public int getReadCounter() {
		return readCounter;
	}

	public void setReadCounter(int readCounter) {
		this.readCounter = readCounter;
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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getRevokedAt() {
		return revokedAt;
	}

	public void setRevokedAt(Date revokedAt) {
		this.revokedAt = revokedAt;
	}

	public boolean isBusinessComplete() {
		return businessComplete;
	}

	public void setBusinessComplete(boolean businessComplete) {
		this.businessComplete = businessComplete;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


}

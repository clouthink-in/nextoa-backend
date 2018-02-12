package in.clouthink.synergy.team.domain.model;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务（以协作请求Activity为例）的处理过程是通过任务驱动的,协作请求的参与者在整个协作请求的流转过程中始终只有一个任务（任务）入口.
 */
@Document(collection = "Tasks")
@CompoundIndexes({@CompoundIndex(name = "message_title_receiver", def = "{title : 1, receiver : 1}"),
				  @CompoundIndex(name = "message_initiator_sender_receiver", def = "{initiator : 1, sender : 1, receiver : 1}")})
public class Task extends StringIdentifier {

	//冗余字段,来自业务主体（协作请求）的分类
	@Indexed
	private String category;

	//冗余字段,来自业务主体（协作请求）的标题
	@Indexed
	private String title;

	@Indexed
	private TaskStatus status;

	//业务主体（协作请求）的类型
	@Indexed
	private BizRefType bizRefType;

	//业务主体（协作请求）-引用id
	@Indexed
	private String bizRefId;

	//产生该任务的动作-引用id(可能为空)
	@Indexed
	private String actionRefId;

	//冗余字段,帖子发起人
	@DBRef
	private User initiator;

	//帖子上一个处理人（发送人）
	@DBRef
	private User sender;

	@Indexed
	@DBRef
	private User receiver;

	//第一次收到任务的时间
	@Indexed
	private Date receivedAt;

	//最近一次修改的时间(第一次收到任务的时候,modifiedAt=receivedAt),任务收到后,用户可处理（回复,转发,手工结束三种方式）,也可以被其他人重新激活（其他人回复,转发给本用户）
	@Indexed
	private Date modifiedAt;

	private boolean read = false;

	//扩展属性,携带一些其他处理信息
	private Map attributes = new HashMap<>();

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		if (status == TaskStatus.PENDING) {
			this.read = false;
		}
		this.status = status;
	}

	public BizRefType getBizRefType() {
		return bizRefType;
	}

	public void setBizRefType(BizRefType bizRefType) {
		this.bizRefType = bizRefType;
	}

	public String getBizRefId() {
		return bizRefId;
	}

	public void setBizRefId(String bizRefId) {
		this.bizRefId = bizRefId;
	}

	public String getActionRefId() {
		return actionRefId;
	}

	public void setActionRefId(String actionRefId) {
		this.actionRefId = actionRefId;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Map getAttributes() {
		return attributes;
	}

	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}
}

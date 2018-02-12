package in.clouthink.synergy.team.domain.model;

/**
 * 协作请求操作类型,在协作请求流转开始到整个生命周期结束过程中发生的所有动作,
 * <p>
 * 其中edit,copy,forward,print取决于是否有权限,
 * <p>
 * revoke只能在未处理的情况下才能才能撤回
 *
 * @author dz
 */
public enum ActivityActionType {

	//发起
	START,
	//回复
	REPLY,
	//编辑
	EDIT,
	//再处理
	COPY,
	//转发
	FORWARD,
	//已读
	READ,
	//结束
	END,
	//撤回
	REVOKE,
	//打印
	PRINT,
	//终止
	TERMINATE

}

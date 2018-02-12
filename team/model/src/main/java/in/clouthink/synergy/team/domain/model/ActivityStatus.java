package in.clouthink.synergy.team.domain.model;

/**
 * 协作请求状态
 */
public enum ActivityStatus {

	//草稿
	DRAFT,
	//流转中
	IN_PROGRESS,
	//已撤回(任务未被处理的时候可撤回)
	REVOKED,
	//终止（预留）
	TERMINATED

}

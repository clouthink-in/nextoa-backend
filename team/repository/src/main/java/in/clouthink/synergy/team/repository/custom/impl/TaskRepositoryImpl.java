package in.clouthink.synergy.team.repository.custom.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Task;
import in.clouthink.synergy.team.domain.model.TaskStatus;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.repository.custom.TaskRepositoryCustom;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * @author dz
 */
@Repository
public class TaskRepositoryImpl extends AbstractCustomRepositoryImpl implements TaskRepositoryCustom {

	@Override
	public Page<Task> queryPage(User receiver,
								TaskQueryRequest queryRequest,
								TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		if (includeOrExcludeStatus == null) {
			includeOrExcludeStatus = TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE;
		}
		Query query = buildQuery(queryRequest, includeOrExcludeStatus);
		if (receiver != null) {
			query.addCriteria(Criteria.where("receiver").is(receiver));
		}

		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "modifiedAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Task.class);
		List<Task> list = mongoTemplate.find(query, Task.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Task> queryPageByActivityCreator(String username, User byWho, Pageable pageable) {
		Query userQuery = new Query();
//		userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
		userQuery.addCriteria(Criteria.where("username").regex(username));

		Query messageQuery = new Query();
		//排除所有已终止的状态
		messageQuery.addCriteria(Criteria.where("status").ne(TaskStatus.TERMINATED));

		messageQuery.addCriteria(Criteria.where("receiver").is(byWho));
		messageQuery.addCriteria(Criteria.where("initiator").in(mongoTemplate.find(userQuery, User.class)));

		messageQuery.with(pageable);
		long count = mongoTemplate.count(messageQuery, Task.class);
		List<Task> list = mongoTemplate.find(messageQuery, Task.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Task> queryPageByReceiver(String username, User byWho, Pageable pageable) {
		Query userQuery = new Query();
//		userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
		userQuery.addCriteria(Criteria.where("username").regex(username));

		Query messageQuery = new Query();
		//排除所有已终止的状态
		messageQuery.addCriteria(Criteria.where("status").ne(TaskStatus.TERMINATED));

		messageQuery.addCriteria(new Criteria().orOperator(Criteria.where("initiator").is(byWho),
														   Criteria.where("sender").is(byWho)));
		messageQuery.addCriteria(Criteria.where("receiver").in(mongoTemplate.find(userQuery, User.class)));

		messageQuery.with(pageable);
		long count = mongoTemplate.count(messageQuery, Task.class);
		List<Task> list = mongoTemplate.find(messageQuery, Task.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public long queryCount(User receiver,
						   TaskQueryRequest queryRequest,
						   TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		if (includeOrExcludeStatus == null) {
			includeOrExcludeStatus = TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE;
		}
		Query query = buildQuery(queryRequest, includeOrExcludeStatus);

		if (receiver != null) {
			query.addCriteria(Criteria.where("receiver").is(receiver));
		}

		return mongoTemplate.count(query, Task.class);
	}

	@Override
	public void markAsRead(String id) {
		mongoTemplate.updateFirst(query(where("id").is(id)), update("read", true), Task.class);
	}

	private Query buildQuery(TaskQueryRequest request,
							 TaskQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		Query query = new Query();
		//分类
		if (!StringUtils.isEmpty(request.getCategory())) {
			query.addCriteria(Criteria.where("category").regex(request.getCategory()));
		}
		//标题
		if (!StringUtils.isEmpty(request.getTitle())) {
			query.addCriteria(Criteria.where("title").regex(request.getTitle()));
		}
		//发起人
		if (!StringUtils.isEmpty(request.getInitiatorUsername())) {
			Query userQuery = new Query();
//			userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
			userQuery.addCriteria(Criteria.where("username").regex(request.getInitiatorUsername()));
			query.addCriteria(Criteria.where("initiator").in(mongoTemplate.find(userQuery, User.class)));
		}
		//状态
		if (request.getTaskStatus() != null) {
			if (TaskQueryRequest.IncludeOrExcludeStatus.INCLUDE == includeOrExcludeStatus) {
				query.addCriteria(new Criteria().andOperator(Criteria.where("status").is(request.getTaskStatus()),
															 Criteria.where("status").ne(TaskStatus.TERMINATED)));
			}
			else if (TaskQueryRequest.IncludeOrExcludeStatus.EXCLUDE == includeOrExcludeStatus) {
				query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne(request.getTaskStatus()),
															 Criteria.where("status").ne(TaskStatus.TERMINATED)));
			}
		}
		else {
			//排除所有已终止的状态
			query.addCriteria(Criteria.where("status").ne(TaskStatus.TERMINATED));
		}
		//时间范围
		if (request.getBeginDate() != null && request.getEndDate() != null) {
			Criteria criteria = new Criteria().andOperator(Criteria.where("receivedAt").gte(request.getBeginDate()),
														   Criteria.where("receivedAt").lte(request.getEndDate()));
			query.addCriteria(criteria);
		}
		else if (request.getBeginDate() != null) {
			query.addCriteria(Criteria.where("receivedAt").gte(request.getBeginDate()));
		}
		else if (request.getEndDate() != null) {
			query.addCriteria(Criteria.where("receivedAt").lte(request.getEndDate()));
		}
		return query;
	}

}

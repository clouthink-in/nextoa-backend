package in.clouthink.synergy.team.repository.custom.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.*;
import in.clouthink.synergy.team.domain.request.TaskQueryRequest;
import in.clouthink.synergy.team.repository.custom.FavoriteTaskRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class FavoriteTaskRepositoryImpl extends AbstractCustomRepositoryImpl implements
		FavoriteTaskRepositoryCustom {

	@Override
	public Page<FavoriteTask> queryPage(User createdBy, TaskQueryRequest queryRequest) {
		Query query = buildQuery(queryRequest);
		if (createdBy != null) {
			query.addCriteria(Criteria.where("createdBy").is(createdBy));
		}

		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, FavoriteTask.class);
		List<FavoriteTask> list = mongoTemplate.find(query, FavoriteTask.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(TaskQueryRequest request) {
		Query query = new Query();
		if (!StringUtils.isEmpty(request.getCategory())) {
			List<Task> tasks = mongoTemplate.find(new Query(Criteria.where("category")
																	.regex(request.getCategory())
																	.andOperator(Criteria.where("status")
																							   .ne(TaskStatus.TERMINATED))),
												  Task.class);
			query.addCriteria(Criteria.where("message").in(tasks));
		}

		if (!StringUtils.isEmpty(request.getTitle())) {
			List<Task> tasks = mongoTemplate.find(new Query(Criteria.where("title")
																	.regex(request.getTitle())
																	.andOperator(Criteria.where("status")
																							   .ne(TaskStatus.TERMINATED))),
												  Task.class);
			query.addCriteria(Criteria.where("message").in(tasks));
		}

		if (!StringUtils.isEmpty(request.getInitiatorUsername())) {
			Query userQuery = new Query();
//			userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
			userQuery.addCriteria(Criteria.where("username").regex(request.getInitiatorUsername()));
			List<Task> tasks = mongoTemplate.find(new Query(Criteria.where("initiator")
																	.in(mongoTemplate.find(userQuery, User.class))
																	.andOperator(Criteria.where("status")
																							   .ne(TaskStatus.TERMINATED))),
												  Task.class);
			query.addCriteria(Criteria.where("message").in(tasks));
		}
		return query;
	}
}

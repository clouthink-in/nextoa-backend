package in.clouthink.synergy.team.repository.custom.impl;

import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.request.ActivityActionQueryRequest;
import in.clouthink.synergy.team.repository.custom.ActivityActionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ActivityActionRepositoryImpl extends AbstractCustomRepositoryImpl implements ActivityActionRepositoryCustom {

	@Override
	public Page<ActivityAction> queryPage(Activity activity, ActivityActionQueryRequest request) {
		Query query = buildQuery(request);
		query.addCriteria(Criteria.where("activity").is(activity));

		int start = request.getStart();
		int limit = request.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, ActivityAction.class);
		List<ActivityAction> list = mongoTemplate.find(query, ActivityAction.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public List<ActivityAction> queryList(Activity activity, ActivityActionQueryRequest request) {
		Query query = buildQuery(request);
		query.addCriteria(Criteria.where("activity").is(activity)).with(new Sort(Sort.Direction.DESC, "createdAt"));
		return mongoTemplate.find(query, ActivityAction.class);
	}

	private Query buildQuery(ActivityActionQueryRequest request) {
		Query query = new Query();

		if (request.getActivityActionTypes() != null && request.getActivityActionTypes().length > 0) {
			query.addCriteria(Criteria.where("type").in(Arrays.asList(request.getActivityActionTypes())));
		}

		return query;
	}

}

package in.clouthink.synergy.shared.repository.custom;

import in.clouthink.synergy.shared.domain.request.DateRangedSearchRequest;
import in.clouthink.synergy.shared.domain.request.PageSearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 */
public class RepositoryCustomHelper {

	public static PageRequest processPagePart(Query query, PageSearchRequest queryRequest, Sort sort) {
		if (queryRequest instanceof DateRangedSearchRequest) {
			DateRangedSearchRequest dateRangedSearchRequest = (DateRangedSearchRequest) queryRequest;
			if (dateRangedSearchRequest.getBeginDate() != null && dateRangedSearchRequest.getEndDate() != null) {
				query.addCriteria(new Criteria().andOperator(Criteria.where("createdAt")
																	 .gte(dateRangedSearchRequest.getBeginDate()),
															 Criteria.where("createdAt")
																	 .lte(dateRangedSearchRequest.getEndDate())));
			}
			else if (dateRangedSearchRequest.getBeginDate() != null) {
				query.addCriteria(Criteria.where("createdAt").gte(dateRangedSearchRequest.getBeginDate()));
			}
			else if (dateRangedSearchRequest.getEndDate() != null) {
				query.addCriteria(Criteria.where("createdAt").lte(dateRangedSearchRequest.getEndDate()));
			}
		}

		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();

		PageRequest pageable = new PageRequest(start, limit, sort);
		query.with(pageable);

		return pageable;
	}

}

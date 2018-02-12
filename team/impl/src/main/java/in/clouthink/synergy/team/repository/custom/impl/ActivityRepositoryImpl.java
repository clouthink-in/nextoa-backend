package in.clouthink.synergy.team.repository.custom.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Activity;
import in.clouthink.synergy.team.domain.request.ActivityQueryRequest;
import in.clouthink.synergy.team.repository.custom.ActivityRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class ActivityRepositoryImpl extends AbstractCustomRepositoryImpl implements ActivityRepositoryCustom {

    @Override
    public Page<Activity> queryPage(User creator,
                                    ActivityQueryRequest request,
                                    ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
        Query query = buildQuery(request, includeOrExcludeStatus);
        if (creator != null) {
            query.addCriteria(Criteria.where("createdBy").is(creator));
        }

        int start = request.getStart();
        int limit = request.getLimit();
        PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
        query.with(pageable);
        long count = mongoTemplate.count(query, Activity.class);
        List<Activity> list = mongoTemplate.find(query, Activity.class);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public long queryCount(User creator,
                           ActivityQueryRequest request,
                           ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
        Query query = buildQuery(request, includeOrExcludeStatus);
        if (creator != null) {
            query.addCriteria(Criteria.where("createdBy").is(creator));
        }

        return mongoTemplate.count(query, Activity.class);
    }

    @Override
    public void updateReadCounter(String id, int readCounter) {
        mongoTemplate.updateFirst(query(where("id").is(id)), update("readCounter", readCounter), Activity.class);
    }

    @Override
    public void markActivityBusinessComplete(Activity activity) {
        mongoTemplate.updateFirst(query(where("id").is(activity.getId())), update("businessComplete", true), Activity.class);
    }

    private Query buildQuery(ActivityQueryRequest request,
                             ActivityQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
        Query query = new Query();

        if (!StringUtils.isEmpty(request.getCategory())) {
            query.addCriteria(Criteria.where("category").regex(request.getCategory()));
        }

        if (!StringUtils.isEmpty(request.getTitle())) {
            query.addCriteria(Criteria.where("title").regex(request.getTitle()));
        }

        if (request.getActivityStatus() != null) {
            if (ActivityQueryRequest.IncludeOrExcludeStatus.INCLUDE == includeOrExcludeStatus) {
                query.addCriteria(Criteria.where("status").is(request.getActivityStatus()));
            }
            else if (ActivityQueryRequest.IncludeOrExcludeStatus.EXCLUDE == includeOrExcludeStatus) {
                query.addCriteria(Criteria.where("status").ne(request.getActivityStatus()));
            }
        }

        if (request.getBeginDate() != null && request.getEndDate() != null) {
            Criteria criteria = new Criteria().andOperator(Criteria.where("createdAt").gte(request.getBeginDate()),
                                                           Criteria.where("createdAt").lte(request.getEndDate()));
            query.addCriteria(criteria);
        }
        else if (request.getBeginDate() != null) {
            query.addCriteria(Criteria.where("createdAt").gte(request.getBeginDate()));
        }
        else if (request.getEndDate() != null) {
            query.addCriteria(Criteria.where("createdAt").lte(request.getEndDate()));
        }

        return query;
    }

}

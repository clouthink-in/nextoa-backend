package in.clouthink.synergy.account.repository.custom.impl;

import in.clouthink.synergy.account.domain.model.Role;
import in.clouthink.synergy.account.domain.request.RoleSearchRequest;
import in.clouthink.synergy.account.repository.custom.RoleRepositoryCustom;
import in.clouthink.synergy.shared.repository.custom.impl.AbstractCustomRepositoryImpl;
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
public class RoleRepositoryImpl extends AbstractCustomRepositoryImpl implements RoleRepositoryCustom {

    @Override
    public Page<Role> queryPage(RoleSearchRequest queryRequest) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.ASC, "code"));

        Query query = buildQuery(queryRequest);
        query.with(pageable);

        long count = mongoTemplate.count(query, Role.class);
        List<Role> list = mongoTemplate.find(query, Role.class);

        return new PageImpl<>(list, pageable, count);
    }

    private Query buildQuery(RoleSearchRequest request) {
        Query query = new Query();

        if (!StringUtils.isEmpty(request.getCode())) {
            query.addCriteria(Criteria.where("code").regex(request.getCode().toUpperCase()));
        }

        if (request.getType() != null) {
            query.addCriteria(Criteria.where("type").is(request.getType()));
        }

        return query;
    }

}

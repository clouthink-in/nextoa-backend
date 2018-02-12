package in.clouthink.synergy.audit.repository;


import in.clouthink.synergy.audit.domain.model.AggregationType;
import in.clouthink.synergy.audit.domain.model.AuditEventAggregation;
import in.clouthink.synergy.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditEventAggregationRepository extends AbstractRepository<AuditEventAggregation> {

	AuditEventAggregation findByRealmAndAggregationTypeAndAggregationKey(String realm,
																		 AggregationType type,
																		 String key);

	List<AuditEventAggregation> findByRealmAndAggregationTypeAndAggregationKeyLike(String realm,
																				   AggregationType type,
																				   String key);

	Page<AuditEventAggregation> findPageByRealmAndAggregationTypeOrderByAggregationKeyDesc(String realm,
																						   AggregationType type,
																						   Pageable pageable);

}

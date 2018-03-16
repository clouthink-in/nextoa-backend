package in.clouthink.synergy.account.repository;


import in.clouthink.synergy.account.domain.model.Group;
import in.clouthink.synergy.shared.repository.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface GroupRepository extends AbstractRepository<Group> {

    List<Group> findByParentOrderByCodeAscNameAsc(Group parent);

    long countByParent(Group parent);

    Group findByName(String name);

    Group findByParentAndName(Group parent, String name);

}
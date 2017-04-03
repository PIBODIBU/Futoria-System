package com.futoria.system.repository;

import com.futoria.core.model.user.User;
import com.futoria.system.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
    Test findFirstByUuid(String uuid);

    Set<Test> getAllByOwner(User owner);
}

package main.repository;

import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FTRUserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User getByUsername(String username);

    User getFirstById(Long id);
}

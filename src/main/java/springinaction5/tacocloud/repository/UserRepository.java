package springinaction5.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import springinaction5.tacocloud.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}

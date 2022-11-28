package springinaction5.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import springinaction5.tacocloud.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

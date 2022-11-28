package springinaction5.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import springinaction5.tacocloud.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}

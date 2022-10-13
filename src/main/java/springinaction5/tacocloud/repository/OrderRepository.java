package springinaction5.tacocloud.repository;

import springinaction5.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);

}

package storeLab.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storeLab.order_service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

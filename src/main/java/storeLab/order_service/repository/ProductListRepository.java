package storeLab.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storeLab.order_service.entity.ProductList;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Integer> {
    void deleteByOrderId(Integer orderId);
    List<ProductList> findAllByOrderId(Integer id);
}

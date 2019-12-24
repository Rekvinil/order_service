package storeLab.order_service.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import storeLab.order_service.entity.CartForOrder;
import storeLab.order_service.entity.Order;
import storeLab.order_service.entity.Product;
import storeLab.order_service.entity.ProductList;
import storeLab.order_service.repository.OrderRepository;
import storeLab.order_service.repository.ProductListRepository;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductListRepository productListRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private final String URL_PRODUCT = "http://localhost:8081";

    public OrderService(OrderRepository orderRepository, ProductListRepository productListRepository) {
        this.orderRepository = orderRepository;
        this.productListRepository = productListRepository;
    }

    public Integer addOrder(CartForOrder[] cart){
        Order order = new Order();
        order.setDate(new Date());
        order.setUserId(cart[0].getUserId());
        float fullPrice = 0;
        for(CartForOrder cart1 : cart){
            Product p = restTemplate.getForObject(URL_PRODUCT+"/productEditing/"+cart1.getProductId(),Product.class);
            fullPrice += p.getPrice()*cart1.getCount()*(1-Float.parseFloat(p.getDiscount())/100);
        }
        System.out.println(fullPrice);
        fullPrice-=cart[0].getDiscount();
        System.out.println(fullPrice);
        order.setPrice(fullPrice);
        Order newOrder = orderRepository.save(order);
        for(CartForOrder cart1 : cart){
            ProductList productList = new ProductList(cart1.getProductId(), newOrder.getId(), cart1.getCount());
            productListRepository.save(productList);
        }
        return newOrder.getId();
    }

    public void deleteOrder(Integer orderId){
        productListRepository.deleteByOrderId(orderId);
        orderRepository.deleteById(orderId);
    }

    public Order getOrder(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order[] getAllOrders(){
        Order[] arr = new Order[orderRepository.findAll().size()];
        orderRepository.findAll().toArray(arr);
        return arr;
    }

    public ProductList[] getProductListByOrder(Integer orderId){
        List<ProductList> product= productListRepository.findAllByOrderId(orderId);
        ProductList[] productLists = new ProductList[product.size()];
        product.toArray(productLists);
        return productLists;
    }


}

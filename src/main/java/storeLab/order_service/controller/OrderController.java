package storeLab.order_service.controller;

import org.springframework.web.bind.annotation.*;
import storeLab.order_service.entity.CartForOrder;
import storeLab.order_service.entity.Order;
import storeLab.order_service.entity.ProductList;
import storeLab.order_service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orderService")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/addOrder")
    public Integer addOrder(@RequestBody CartForOrder[] cart){
        return orderService.addOrder(cart);
    }

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable Integer id){
        return orderService.getOrder(id);
    }

    @GetMapping("/getAllOrders")
    public Order[] getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
    }

    @GetMapping("/getProductList/{id}")
    public ProductList[] getProductList(@PathVariable Integer id){
        return orderService.getProductListByOrder(id);
    }


}

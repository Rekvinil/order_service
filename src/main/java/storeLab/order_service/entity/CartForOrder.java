package storeLab.order_service.entity;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CartForOrder {
    private Integer id;

    private Integer userId;

    private Integer productId;
    private Integer count;
    private Integer discount;

    public CartForOrder() {
    }

    public CartForOrder(Integer id, Integer userId, Integer productId, Integer count, Integer discount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}


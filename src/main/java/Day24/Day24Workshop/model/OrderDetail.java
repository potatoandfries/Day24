package Day24.Day24Workshop.model;

public class OrderDetail {
    
    private Integer id;
    private Integer order_id;
    private String product;
    private Integer quantity;
    private float unit_price;
    private float discount;

    

    public OrderDetail() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public float getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    
}

package Day24.Day24Workshop.model;

import java.util.Date;
import java.util.List;

public class Order {
    
    private int order_id;
    private Date order_date;
    private String customer_name;
    private String ship_address;
    private String notes;
    private float tax;
    private List<OrderDetail> od;

    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getShip_address() {
        return ship_address;
    }
    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public float getTax() {
        return tax;
    }
    public void setTax(float tax) {
        this.tax = tax;
    }
    public List<OrderDetail> getOd() {
        return od;
    }
    public void setOd(List<OrderDetail> od) {
        this.od = od;
    }
    public Order() {
    }

    


    
    
}

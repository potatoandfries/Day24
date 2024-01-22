package Day24.Day24Workshop;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.model.OrderDetail;

public class Utils {
    
    public static Order toOrder(SqlRowSet rs) {
        Order o = new Order();
        o.setOrder_id(rs.getInt("order_id"));
        o.setOrder_date(rs.getDate("order_date"));
        o.setCustomer_name(rs.getString("customer_name"));
        o.setShip_address(rs.getString("ship_address"));
        o.setNotes(rs.getString("notes"));
        o.setTax(rs.getFloat("tax"));
        return o;
    }
    
    public static OrderDetail toOrderDetail(SqlRowSet rs){
        OrderDetail od = new OrderDetail();
        od.setId(rs.getInt("id"));
        od.setOrder_id(rs.getInt("order_id"));
        od.setProduct(rs.getString("product"));
        od.setQuantity(rs.getInt("quantity"));
        od.setUnit_price(rs.getFloat("unit_price"));
        od.setDiscount(rs.getFloat("discount"));
        return od;
    }
    
   
}
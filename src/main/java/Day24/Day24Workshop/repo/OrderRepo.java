package Day24.Day24Workshop.repo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.model.OrderDetail;

@Repository
public class OrderRepo {
    
    @Autowired
    JdbcTemplate template;

    public boolean InsertOrderItems(int order_id, List<OrderDetail> orderdetails){
        //memorise this fucking loop*
        int count = 0;
        for (int i=0;i< orderdetails.size();i++){
            OrderDetail d = orderdetails.get(i);
            count += template.update(Queries.SQL_INSERT_ORDER_DETAILS,order_id,d.getProduct(),d.getQuantity());
        }
        return count== orderdetails.size(); // if its equal means all is transfered
    }

    public boolean InsertOrder(Order order){    
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int orderResult = template.update
            (connection ->{
            PreparedStatement ps = connection.prepareStatement(Queries.SQL_INSERT_ORDERS,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getCustomer_name());
            ps.setString(2, order.getShip_address());
            ps.setString(3, order.getNotes());
            return ps;

        },keyHolder);
        if (!(orderResult>0)){
            return false;
        }
        else{
            int orderId = keyHolder.getKey().intValue();
            if (InsertOrderItems(orderId, order.getOd())){
                return true;
            }
            return false;
        }
    }
    
}

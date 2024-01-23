package Day24.Day24Workshop.repo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class OrderRepo {
    
    @Autowired
    JdbcTemplate template;
    //one order man order detail*
    public boolean insertOrder(String customer_name,String ship_address,String notes){
        return template.update(Queries.SQL_INSERT_ORDERS,customer_name,ship_address,notes)>=1;

    }  

    public boolean insertOrderDetail(Integer order_id,String product,Integer quantity){
        return template.update(Queries.SQL_INSERT_ORDER_DETAILS,order_id,product,quantity)>=1;
    }

}

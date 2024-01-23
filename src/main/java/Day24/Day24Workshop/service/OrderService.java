package Day24.Day24Workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Day24.Day24Workshop.errors.OrderError;
import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.model.OrderDetail;
import Day24.Day24Workshop.repo.OrderRepo;
@Service
public class OrderService {
    
    @Autowired
    private OrderRepo repo;

    @Autowired
    AutoIncrementer incrementer;

    //the service is where the business logic will be implemented.
    @Transactional(rollbackFor = OrderError.class)
    public boolean createOrder(Order order) throws OrderError {
    //this is for random >> if u want just set it as 1* since int auto increment*
    //String oId = UUID.randomUUID().toString().substring(0, 3);
    //String oId = Random.from(null)
    //System.out.println(oId);
    //order.setOrder_id(Integer.parseInt(oId));
    order.setOrder_id(incrementer.getNextNumber());

        boolean orderInserted = repo.insertOrder(order.getCustomer_name(), order.getShip_address(), order.getNotes());

        if (!orderInserted) {
            return false; // Handle the case where inserting the order failed
        }

        // Insert each order detail
        for (OrderDetail orderDetail : order.getOd()) {
            boolean orderDetailInserted = repo.insertOrderDetail(order.getOrder_id(), orderDetail.getProduct(), orderDetail.getQuantity());
            if (!orderDetailInserted) {
            
                return false; // Handle the case where inserting an order detail failed
            }
        }

            return true; // If everything is inserted successfully
        }
}     

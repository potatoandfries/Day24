package Day24.Day24Workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Day24.Day24Workshop.errors.OrderError;
import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.repo.OrderRepo;

public class OrderService {
    
    @Autowired
    private OrderRepo repo;


// if it eats a orderError exception it will roll back*
    @Transactional (rollbackFor = OrderError.class)
    public void createOrder(Order order) throws OrderError{
        if (!(repo.InsertOrder(order))){
            throw new OrderError();
        }
    }
}

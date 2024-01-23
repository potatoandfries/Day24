package Day24.Day24Workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.model.OrderDetail;
import Day24.Day24Workshop.repo.OrderRepo;
import Day24.Day24Workshop.service.OrderService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LandingPage {
    
    @Autowired
    OrderService svc;

    @Autowired
    OrderRepo repo;

    @GetMapping(path={"/"})
    public ModelAndView showForm() {
        ModelAndView mav = new ModelAndView("index");
        Order order = new Order(); // Assuming this initializes an empty Order object
        mav.addObject("order", order); // Changed from "Order" to "order"
        return mav;
    }

    @PostMapping(path={"/order"}, consumes = "application/x-www-form-urlencoded")
    public ModelAndView register(@ModelAttribute("order") Order order, HttpSession session){
        try {
            System.out.println("Submitted data: " + order);
            svc.createOrder(order);
            
            // Store the order in the session
            session.setAttribute("order", order);
            
            return new ModelAndView("redirect:/submit");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }

    @GetMapping(path={"/submit"})
    public ModelAndView showSuccessPage() {
        ModelAndView mav = new ModelAndView("submit");
        OrderDetail orderDetail = new OrderDetail();
        mav.addObject("OrderDetail", orderDetail);
        return mav;
    }

    @PostMapping(path={"/submit/success"}, consumes = "application/x-www-form-urlencoded")
    public ModelAndView registerDetails(@ModelAttribute("OrderDetail") OrderDetail orderDetail, HttpSession session) {
    Order order = (Order) session.getAttribute("order");

    // Create a new list and store your data inside
    List<OrderDetail> result = new LinkedList<>();
    
    // Add the order detail into the list
    result.add(orderDetail);
    
    // Assuming you want to insert order details and get the result
    boolean insertionResult = repo.InsertOrderItems(order.getOrder_id(), result);

    ModelAndView mav = new ModelAndView("success");
    
    // Add the result to the ModelAndView
    mav.addObject("insertionResult", insertionResult);
    
    return mav;
}

}


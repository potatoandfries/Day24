package Day24.Day24Workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import Day24.Day24Workshop.errors.OrderError;
import Day24.Day24Workshop.model.Order;
import Day24.Day24Workshop.model.OrderDetail;
import Day24.Day24Workshop.service.OrderService;
import jakarta.servlet.http.HttpSession;



@Controller
public class LandingPage {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ModelAndView showLandingPage() {
        ModelAndView mav = new ModelAndView("index"); // index.html
        mav.addObject("orderdetail", new OrderDetail()); // Add an empty OrderDetail to the model
        return mav;
    }

    @PostMapping("/order")
    public ModelAndView addProduct(@ModelAttribute("orderdetail") OrderDetail orderDetail, HttpSession session) {
        ModelAndView mav = new ModelAndView("index"); // index.html

        // Handle the addition of a product (you can add it to a list in session or your data model)
        List<OrderDetail> orderDetailsList = (List<OrderDetail>) session.getAttribute("orderDetailsList");
        if (orderDetailsList == null) {
            orderDetailsList = new LinkedList<>();
            session.setAttribute("orderDetailsList", orderDetailsList);
        }
        orderDetailsList.add(orderDetail);

        mav.addObject("orderdetail", new OrderDetail()); // Clear the form
        return mav;
    }

    @PostMapping("/checkout")
    public ModelAndView showCheckoutPage() {
        ModelAndView modelAndView = new ModelAndView("checkout"); // checkout.html
        modelAndView.addObject("order", new Order()); // Add an empty Order to the model
        return modelAndView;
    }

    @PostMapping("/checkout/check")
    public ModelAndView checkout(@ModelAttribute("order") Order order, HttpSession session) {
        ModelAndView mav = new ModelAndView("checkout"); // checkout.html

        // Retrieve the list of order details from the session
        List<OrderDetail> orderDetailsList = (List<OrderDetail>) session.getAttribute("orderDetailsList");

        if (orderDetailsList != null) {
            // Set the list of order details in the Order object
            order.setOd(orderDetailsList);
        }

        try {
            // Call the service to create the order
            boolean result = orderService.createOrder(order);
            if (result) {
                mav.setViewName("ok");
                return mav;
            } else {
                mav.setViewName("notok");
                return mav;
            }
        } catch (OrderError e) {
            mav.addObject("errorMessage", "Error occurred during order creation.");
        }

        return mav;
    }
}

package springreactjunit.demo.controller;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import springreactjunit.demo.model.Customer;
import springreactjunit.demo.model.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class MainController {

    @GetMapping("/main")
    public Orders mainPage(){
        Orders orders = new Orders();

        orders.setCustid("cust15176166");
        orders.setDate(LocalDate.now());
        orders.setOrderNo("or15176166");

        Customer customer = new Customer();
        customer.setCustid("custId1858161");
        customer.setAddress("Philippines");
        customer.setCity("Antipolo");
        customer.setFname("Fname");
        customer.setLname("Lname");
        customer.setZip("8610");
        customer.setState("State");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        orders.setCustomerList(customerList);

        return orders;
    }
    @PostMapping("/add")
    public String addOrder(@RequestBody Orders order){

        return "New order has been added";
    }


}

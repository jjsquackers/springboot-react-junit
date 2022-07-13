package springreactjunit.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import springreactjunit.demo.model.Customer;
import springreactjunit.demo.model.Orders;
import springreactjunit.demo.service.WebClientAPI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/orders")
@CrossOrigin
public class MainController {


    @GetMapping("/main")
    public Orders mainPage() {
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
    public String addOrder(@RequestBody Orders order) {

        return "New order has been added";
    }

    @GetMapping("/consume")
    public ResponseEntity<?> consume() {
        try {
            String url = "http://localhost:8080/orders/main";
            var restTemplate = new RestTemplate();

            String result = restTemplate.getForObject(url, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }


    private WebClient.Builder webClientBuilder = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    @GetMapping("/web")
    public Object executeWebClient() {
        return webClientBuilder.build()
                .get()
//                .uri("http://localhost:8080/orders/main")
                .uri("http://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @PostMapping("/post")
    public Object post() {
        Object responseBody = new Object();
        return webClientBuilder.build()
                .post()
                .uri("http://jsonplaceholder.typicode.com/users")
                .bodyValue(responseBody)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

    }
}

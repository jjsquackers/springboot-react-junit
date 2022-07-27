package springreactjunit.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import springreactjunit.demo.model.Customer;
import springreactjunit.demo.model.OrderRequestBody;
import springreactjunit.demo.model.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
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

    /*
        Configuring it to Spring Bean
        Purpose: so that it will not be created everytime whenever it will be used on runtime.
     */
    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    // Normal initialization
    private WebClient.Builder webClientBuilder = WebClient.builder();

    @GetMapping("/get/{id}")
    public Object getParam(@PathVariable("id") String id) {
        Object responseBody = new Object();

        return webClientBuilder.build()
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts/" + id)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    //    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @PostMapping("/add")
    public String addOrder(@RequestBody OrderRequestBody orderRequestBody) {

        return "New order has been added: " + orderRequestBody.toString();
    }

    @PostMapping("/post")
    public String postAndRequestBody(@RequestBody OrderRequestBody orderRequestBody) {

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/orders/add")
                .bodyValue(orderRequestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String testWebClientGETPARAM(String caseId) {
        WebClient.Builder webClientBuilder = WebClient.builder();

        return webClientBuilder.build()
                .get()
                .uri("http://localhost: 8069/cas/ad/transfer/internal/v1/trustee/transferData/view/{caseId}?caseId="+caseId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

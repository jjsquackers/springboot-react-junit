package springreactjunit.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import springreactjunit.demo.model.Orders;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class WebClientAPI {
    String url1 = "http://localhost:8080/orders/main";
    String url2 = "";


    public void execute() {


//        WebClient client = WebClient.create();
//        ResponseEntity<Orders> response = client.get()
//                .uri(url1)
//                .retrieve()
//                .toEntity(Orders.class)
//                .block();
//
//
    }

    public void executeWebClient() {
//        WebClient.Builder builder = null;
//        WebClient webClient = builder.baseUrl("http://localhost:8080/orders").build();
//        Object object = webClient
//                .get()
//                .uri("/main")
//                .retrieve()
//                .bodyToMono(Object[].class)
//                .block();
//        System.out.println(
//                object
//
//        );
    }

    WebClient.Builder builder = WebClient.builder();

    WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    public Object orderNow() {
        return webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Object.class);
    }
}

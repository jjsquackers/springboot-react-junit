package springreactjunit.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/main")
    public String mainPage(){

        return "AWESOME!";
    }
}
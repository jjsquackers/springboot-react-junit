package springreactjunit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springreactjunit.demo.service.JsonParser;
import springreactjunit.demo.service.XMLGenerator;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
public class SpringReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactApplication.class, args);

//		XMLGenerator xmlGenerator = new XMLGenerator();
//		try {
//			xmlGenerator.generateXML();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        JsonParser jsonParser = new JsonParser();
        try {
            jsonParser.parseJsonString();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

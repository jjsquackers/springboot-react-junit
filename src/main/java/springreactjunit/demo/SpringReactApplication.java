package springreactjunit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springreactjunit.demo.service.XMLGenerator;

import java.io.IOException;

@SpringBootApplication
public class SpringReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);

		XMLGenerator xmlGenerator = new XMLGenerator();
		try {
			xmlGenerator.generateXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

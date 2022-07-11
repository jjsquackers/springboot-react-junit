package springreactjunit.demo.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
//@EnableAutoConfiguration(
//        exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = "springreactjunit.demo.*")
//@EntityScan("springreactjunit.demo.*")
//@EnableJpaRepositories(basePackages = "springreactjunit.demo.repository")
public class SpringFoxConfig {
    /*
        Access swagger using URL: http://localhost:8080/swagger-ui.html
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .select()

//                .paths(PathSelectors.ant("/foos/*"))
//                .build()
//                .apiInfo(apiInfo());
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("springreactjunit.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                null,
//                new Contact("John Doe", "www.example.com", "myeaddress@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
////    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}

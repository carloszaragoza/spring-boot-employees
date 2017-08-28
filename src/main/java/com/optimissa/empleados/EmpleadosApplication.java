package com.optimissa.empleados;

import static springfox.documentation.builders.PathSelectors.any;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmpleadosApplication {

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Employee").select()
                .apis(RequestHandlerSelectors.basePackage("com.optimissa.empleados"))
                .paths(any()).build().apiInfo(new ApiInfo("Employee Services",
                        "A set of services to provide data access to employees", "1.0.0", null,
                        new Contact("Ricardo Zaragoza Solis", "zaragozar83@gmail.com", null),null, null));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(EmpleadosApplication.class, args);
	}
}

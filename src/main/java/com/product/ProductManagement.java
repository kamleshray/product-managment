  package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class ProductManagement {
	public static void main(String[] args) {
		SpringApplication.run(ProductManagement.class, args);
	}
	@Bean
	  public Docket demoApi() {
	    return new Docket(DocumentationType.SWAGGER_2)// <3>
	        .select()// <4>
	        .apis(RequestHandlerSelectors.any())// <5>
	        .paths(Predicates.not(PathSelectors.regex("/error.*")))// avoid
	                                    // basic-error-controll
	        .build();
	    // http://localhost:8085/swagger-ui.html
	  }

}

package com.productscrud.products;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
		
		public static final Contact DEFAULT_CONTACT =
				new Contact("Vaibhavi Mujumdar", "", "vaibhavim9@yahoo.com");
		public static final ApiInfo DEFAULT =
				new ApiInfo("Product SpringBoot CRUD Application", "Api Documentation for Product CRUD Application", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
	          new ArrayList<VendorExtension>());


		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT);
		}
}

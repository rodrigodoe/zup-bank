package br.com.rodrigodoe.zupbank.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.rodrigodoe.zupbank"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfor());
	}

	private ApiInfo apiInfor() {
		return new ApiInfo("ZupBank API"
						, "Spring boot 2.3.4.RELEASE"
						, "V1", "Terms of Service"
						, new Contact("Rodrigo Carvalho", "wwww.rodrigodoe.com.br", "rrodgcar@gmail.com")
						, "License of API"
						, "LIcense of URL",
						Collections.emptyList());
	}
}

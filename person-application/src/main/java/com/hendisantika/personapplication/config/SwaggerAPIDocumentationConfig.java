package com.hendisantika.personapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IntelliJ IDEA.
 * Project : person-application
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/11/18
 * Time: 06.53
 * To change this template use File | Settings | File Templates.
 */

@EnableSwagger2
@Configuration
public class SwaggerAPIDocumentationConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("PersonEntity REST CRUD operations API in Spring-Boot 2")
                .description(
                        "Sample REST API for monitoring using Spring Boot, Prometheus and Graphana ")
                .termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new Contact("Hendi Santika", "https://github.com/hendisantika/service-monitoring/person-application", "https://github.com/hendisantika")).build();
    }

    @Bean
    public Docket configureControllerPackageAndConvertors() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.hendisantika.personapplication.controller")).build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}

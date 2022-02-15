package io.novelis.smartroby.sample.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket person() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(generateApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.novelis.smartroby.sample.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfo("Person API", "Manage persons", "1.0.0",
                "https://www.novelis.io",
                new Contact("Oussama AMARA","", "oamara@novelis.io"),
                "", "", new ArrayList<>());
    }

    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().displayRequestDuration(true).showExtensions(true)
                .defaultModelRendering(ModelRendering.MODEL).validatorUrl("").operationsSorter(OperationsSorter.ALPHA)
                .tagsSorter(TagsSorter.ALPHA)
                .build();
    }
}

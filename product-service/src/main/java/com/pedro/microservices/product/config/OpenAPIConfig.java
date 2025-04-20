package com.pedro.microservices.product.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Service")
                .description("Product Service API Documentation")
                .version("0.0.1")
                .license(new License().name("Apache 2.0").url("https://apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Product Service Wiki Documentation")
                        .url("https://product-service.wiki.github.com"));
    }
}

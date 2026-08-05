package com.RestfulApi.TeacherInformationSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Teacher Information System API")
                        .version("1.0.0")
                        .description("REST API for managing teachers, students, school classes, and managers.")
                        .contact(new Contact()
                                .name("Haluk Kılınçer")
                                .url("https://github.com/Erwinya"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Local development")
                ));
    }
}

package com.example.usermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
public class OpenApiConfig {
    
    @Value("${server.servlet.context-path:/}")
    private String contextPath;
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("用户管理系统 API")
                        .version("1.0.0")
                        .description("基于 Spring Boot 3 + MyBatis-Plus 的用户管理系统 RESTful API 文档")
                        .contact(new Contact()
                                .name("User Management Team")
                                .email("admin@example.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080" + contextPath)
                                .description("本地开发服务器")
                ));
    }
}

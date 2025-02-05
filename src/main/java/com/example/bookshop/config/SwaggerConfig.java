package com.example.bookshop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Token";
        final String apiTitle = String.format(StringUtils.capitalize("Book Shop"));
        final String[] developer = {"Mirzayev Bekzod"};
        final String apiDescription = String.format(
                """
                        Ushbu Rest Api %s uchun yaratilgan. \s
                        
                        Dasturchi %s
                        """,
                apiTitle,
                Arrays.toString(developer)
        );

        return new OpenAPI()
                .addServersItem(new Server().url("/books-api").description("Root API server"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName
                                , new SecurityScheme()
                                        .description("JSON Web Token")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")

                        )
                )
                .info(new Info()
                        .title(apiTitle)
                        .version("2.0")
                        .description(apiDescription)
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));

    }
}

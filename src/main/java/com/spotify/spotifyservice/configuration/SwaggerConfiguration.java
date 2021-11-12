package com.spotify.spotifyservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.spotify.spotifyservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(info());
    }


    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title("Spotify Service")
                .description("Service of registration, search and return of Tracks, Artist and Album for music reproduction platform via streaming")
                .version("1.0.0")
                .build();
    }
}

package com.ssgtarbucks;



import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


//Swagger2: http://localhost:8080/swagger-ui.html
//Swagger3: http://localhost:8090/swagger-ui/index.html
@Configuration
@EnableWebMvc
public class SwaggerConfig {
	
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.OAS_30)
	 * .useDefaultResponseMessages(false) .select() .apis(RequestHandlerSelectors
	 * .basePackage("com.example.controller")) .paths(PathSelectors.any()) .build();
	 * }
	 */
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .securityContexts(Arrays.asList(securityContext())) // 추가
                .securitySchemes(Arrays.asList(apiKey(), new ApiKey("jwtauthtoken", "jwtauthtoken", "header"))) // 추가
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssgtarbucks.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
	// 추가
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }
 // 추가
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes),
        		        new SecurityReference("jwtauthtoken", authorizationScopes));
    }
    
 // 추가
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Backend API")
                .description("Backend API 문서")
                .version("1.0")
                .build();
    }
}

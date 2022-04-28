package com.cz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author chaixuhong
 * @date 2019-08-12
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enableSwagger;


    @Bean(value = "api")
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("cz后台管理系统接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("chaixuhong", "http://www.chaixuhong.com", "75918847@qq.com"))
                        .version("1.0")
                        .build())
                .groupName("2.X版本")//分组名称
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cz.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

package com.itl.iap.swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    protected Environment env;


    /**
     * 可以定义多个组
     *
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itl.iap"))
                .paths(PathSelectors.any())
                .build();
    }
  /*  @Bean
    public Docket storeApi() {

        String paths = env.getProperty("swagger.paths");

        if (StringUtil.isEmpty(paths)) {
//            paths = "/.*";
            paths = "/non-exist/";
        }

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(ApiInfo())
            .forCodeGeneration(true)
            .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
            .genericModelSubstitutes(GenericResponse.class)
            .select()
//            .paths(PathSelectors.any())
            .paths(regex(paths))//过滤的接口
            .build();

        return docket;

    }*/


    private ApiInfo ApiInfo() {
        return new ApiInfoBuilder()
            .title("门户网站 restful api")//大标题
            .description("门户网站 rest api")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("portal")
            .contact(new Contact("luzhuhong", "", ""))//作者
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }

}
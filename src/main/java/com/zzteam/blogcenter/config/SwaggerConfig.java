package com.zzteam.blogcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//交给spring 容器管理
@Configuration
@EnableSwagger2   //开启Swagger2
public class SwaggerConfig {
    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //如果不想启用swagger 可以在这里加一个.enable(false) 官方默认为true
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage：指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.zzteam.blogcenter.controller"))
                //过滤路径的话 可以加一个.payhs(PathSelectors.ant("要过滤掉的路径"))
                .build()
                ;
    }
    private ApiInfo apiInfo(){
        //这就是一个配置作者信息的
        Contact contact = new Contact("zan","https://www.baidu.com","2834102079@qq.com");
        return new ApiInfo("博客项目",
                "一个博客项目，技术栈为SpringBoot+Vue",
                "v1.0",
                "https://www.baidu.com",
                contact,"Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}

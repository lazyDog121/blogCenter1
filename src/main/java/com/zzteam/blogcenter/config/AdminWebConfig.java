package com.zzteam.blogcenter.config;

import com.zzteam.blogcenter.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////定制web功能
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义排除swagger访问的路径配置
        String[] swaggerExcludes = new String[]{"/swagger-ui.html","/swagger-resources/**","/webjars/**"};
        //registry相当于整个拦截器的注册中心
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//告诉我们要拦截哪些路径 /**默认拦截我们所有的请求
                .excludePathPatterns("/",
                        "/user/register",
                        "/user/login",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                );//要放行的路径
    }
}

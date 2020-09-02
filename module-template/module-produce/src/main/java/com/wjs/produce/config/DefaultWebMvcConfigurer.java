package com.wjs.produce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wenjs
 */
@Configuration
public class DefaultWebMvcConfigurer {

    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               // registry.addInterceptor(new LoginInterceptor());
            }


            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
//                .allowedOrigins("http://10.204.125.44:10001","http://10.204.125.41:8080","http://10.204.125.42:8012","http://amserver1.isky.com:8400","10.204.125.208:8400","http://10.204.125.117:8080","http://big04.isky.com:8400","http://big01.isky.com:8400")
//                .allowCredentials(true)
//                // 预检请求的有效期，单位为秒。
//                .maxAge(1800);
//                .allowedOrigins("http://10.204.125.40:8092","http://10.204.125.40:8080","http://127.0.0.1:8080")
//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true).maxAge(3600);
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .exposedHeaders(HttpHeaders.SET_COOKIE)
                        // 预检请求的有效期，单位为秒。
                        .maxAge(1800);
            }

        };
    }
}


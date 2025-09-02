package com.mm.common.config;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mm.common.interceptor.JwtInterceptor;
import com.mm.common.xss.XssFilter;

/**
 * WebMvc配置
 *
 * @author lwl
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor());
    }

    /**
     * 支持CORS跨域访问
     *
     * @param
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern(CorsConfiguration.ALL);
        config.setAllowCredentials(true);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    /**
     * xss
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xss() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>(new XssFilter());
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

}
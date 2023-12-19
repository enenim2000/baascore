package com.sahaplus.baascore.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${oauth.server.client-id}")
    String authServerClientId;

    @Value("${spring.application.name}")
    String serviceName;

    @Value("${oauth.server.base.url}")
    String authServerBaseUrl;

    @Value("${saha.company.client.id}")
    String sahaCompanyClientId;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
        authenticationInterceptor.setServiceName(serviceName);
        authenticationInterceptor.setAuthServerClientId(authServerClientId);
        authenticationInterceptor.setAuthServerBaseUrl(authServerBaseUrl);
        authenticationInterceptor.setSahaCompanyClientId(sahaCompanyClientId);
        registry.addInterceptor(authenticationInterceptor)
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**");
    }
}

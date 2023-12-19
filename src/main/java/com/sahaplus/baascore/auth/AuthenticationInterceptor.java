package com.sahaplus.baascore.auth;

import com.google.gson.Gson;
import com.sahaplus.baascore.bankone_apis.util.BaseResponse;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.exception.UnauthorizedException;
import com.sahaplus.baascore.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private String authServerBaseUrl;

    private String authServerClientId;

    private String serviceName;


    private String sahaCompanyClientId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        log.info("AuthenticationInterceptor::preHandle()");
//
//        if (!(handler instanceof HandlerMethod handlerMethod)) {
//            return true;
//        }
//
//        if (!isSecuredRoute(handlerMethod)) {
//            return true;
//        }
//
//        String permission = handlerMethod.getMethod().getDeclaredAnnotation(Permission.class).value();
//        String token = request.getHeader("Authorization");
//
//        if (token == null || token.trim().isEmpty()) {
//            throw new UnauthorizedException("Missing Token");
//        }
//
//        token = token.replace("Bearer", "").trim();
//
//        return isPermitted(token, permission);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("AuthenticationInterceptor::postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("AuthenticationInterceptor::afterCompletion()");
    }

    public void setSahaCompanyClientId(String sahaCompanyClientId) {
        this.sahaCompanyClientId = sahaCompanyClientId;
    }

    private boolean isSecuredRoute(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().isAnnotationPresent(
            Permission.class);
    }

    public void setAuthServerClientId(String authServerClientId) {
        this.authServerClientId = authServerClientId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setAuthServerBaseUrl(String authServerBaseUrl) {
        this.authServerBaseUrl = authServerBaseUrl;
    }

    private boolean isPermitted(String token, String permission) {
        String permissionId = HashUtil.getHash(serviceName + permission);


        Map<String, String> request = new HashMap<>();
        request.put("serviceClientId", authServerClientId);
        request.put("token", token);
        request.put("permissionId", permissionId);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("client-id", sahaCompanyClientId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(request, headers);
        log.info("Request: {}", new Gson().toJson(request));
        String oauthUrl = authServerBaseUrl + "/oauth/token/verify";
        log.info("Url: {}", oauthUrl);
        try {
            //The endpoint on the authorization server will check to see if the service has the permissionId in the ApplicationPermission table
            ResponseEntity<String> response = restTemplate.exchange(oauthUrl, HttpMethod.POST, httpEntity, String.class);
            log.info("Api Response: {}", response);
            if (response.getStatusCode().is2xxSuccessful()) {
                AuthToken newToken = new Gson().fromJson(response.getBody(), AuthToken.class);
                RequestUtil.setAuthToken(newToken);
                return true;
            }

        } catch (HttpClientErrorException e) {
            log.error("Unable to verify token server url: {}", oauthUrl);
            log.error("Exception: ", e);
            BaseResponse errorResponse = new Gson().fromJson(e.getResponseBodyAsString(), BaseResponse.class);
            throw new ApiException(errorResponse.getResponseMessage());

        } catch (Exception e) {
            log.error("Unable to verify token server url: {}", oauthUrl);
            log.error("Exception: ", e);
            throw new ApiException("System Malfunction");
        }
        return false;
    }
}

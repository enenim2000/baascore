package com.sahaplus.baascore.auth;

import com.google.gson.Gson;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.exception.UnauthorizedException;
import com.sahaplus.baascore.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private String authServerBaseUrl;

    private String authServerClientId;

    private String serviceName;

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

    private boolean isPermitted(String forwardedToken, String permission) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();

        //Hash SHA 256 of appName,http method,uri e.g user-service,GET,/api/user/logout
        String permissionId = HashUtil.getHash(serviceName + permission);

        header.add("x-auth-client-id", authServerClientId); //Service/App client id
        header.add("x-auth-client-token", forwardedToken); //Token forwarded by API Gateway or frontend client
        header.add("x-auth-permission-id", permissionId);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, header);
        String oauthUrl = authServerBaseUrl + "/oauth/token/verify";
        try {
            //The endpoint on the authorization server will check to see if the service has the permissionId in the ApplicationPermission table
            ResponseEntity<String> response = restTemplate.exchange(oauthUrl, HttpMethod.POST, httpEntity, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                AuthToken newToken = new Gson().fromJson(response.getBody(), AuthToken.class);
                RequestUtil.setAuthToken(newToken);
                return true;
            }
        } catch (Exception e) {
            log.error("Unable to verify token server url: {}", oauthUrl);
        }

        return false;
    }
}

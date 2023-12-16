package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.auth.Permission;
import com.sahaplus.baascore.util.SyncPermissionRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class PermissionService {

  @Value("${spring.application.name}")
  private String applicationName;
  public SyncPermissionRequest generatePermissionRequest(RequestMappingHandlerMapping requestMappingHandlerMapping) {
    Map<RequestMappingInfo, HandlerMethod> endpoints = requestMappingHandlerMapping.getHandlerMethods();
    Iterator<Map.Entry<RequestMappingInfo, HandlerMethod>> it = endpoints.entrySet().iterator();
    HandlerMethod handlerMethod;
    RequestMappingInfo requestInfo;
    String permission;
    SyncPermissionRequest syncPermissionRequest = new SyncPermissionRequest();
    syncPermissionRequest.setAppName(applicationName);
    syncPermissionRequest.setPermissions(new ArrayList<>());

    while (it.hasNext()) {
      Map.Entry<RequestMappingInfo, HandlerMethod> pair = it.next();
      handlerMethod = pair.getValue();
      requestInfo = pair.getKey();
      if(handlerMethod.hasMethodAnnotation(Permission.class)) {
        permission = handlerMethod.getMethod().getDeclaredAnnotation(Permission.class).value();
        Optional<String> pathUri = requestInfo.getDirectPaths().stream().findFirst();

        String method = HttpMethod.POST.name();
        if (handlerMethod.getMethod().isAnnotationPresent(GetMapping.class)) {
          method = HttpMethod.GET.name();
        } else if (handlerMethod.getMethod().isAnnotationPresent(PutMapping.class)) {
          method = HttpMethod.PUT.name();
        } else if (handlerMethod.getMethod().isAnnotationPresent(PatchMapping.class)) {
          method = HttpMethod.PATCH.name();
        } else if (handlerMethod.getMethod().isAnnotationPresent(DeleteMapping.class)) {
          method = HttpMethod.DELETE.name();
        }

        syncPermissionRequest.getPermissions().add(
                SyncPermissionRequest.Data.builder()
                        .permission(permission)
                        .description(handlerMethod.getMethod().getDeclaredAnnotation(Operation.class).summary())
                        .httpMethod(method)
                        .isSecured(true)
                        .uriPath(pathUri.orElse(""))
                        .build());
      }
    }

    return syncPermissionRequest;
  }
}

package com.projectmanagement.controller.interceptor;

import com.projectmanagement.domain.enums.RoleEnum;
import com.projectmanagement.controller.annotaiton.RequirePermission;
import com.projectmanagement.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService; // Kullanıcının yetkilerini kontrol eden servis

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            RequirePermission requirePermission = handlerMethod.getMethodAnnotation(RequirePermission.class);

            if (requirePermission != null) {
                RoleEnum required = requirePermission.value();

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                if (auth == null || !auth.isAuthenticated()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return false;
                }

                boolean hasRole = auth.getAuthorities().stream()
                        .anyMatch(a -> Objects.equals(a.getAuthority(), required.name()));

                if (!hasRole) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                    return false;
                }
            }
        }

        return true; // yetki gerekiyorsa ve varsa geç
    }


}

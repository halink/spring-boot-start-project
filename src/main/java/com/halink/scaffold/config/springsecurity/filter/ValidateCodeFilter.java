package com.halink.scaffold.config.springsecurity.filter;

import com.halink.scaffold.common.constant.ExceptionMessageConstants;
import com.halink.scaffold.common.exception.CustomAuthenticationException;
import com.halink.scaffold.core.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户名密码验证前的所有验证都可以在这里处理
 *
 * @author halink
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String loginUrl = "/api/login";
        if (StringUtils.equalsIgnoreCase(loginUrl, request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            String code = request.getParameter("imageCode");
            // TODO 测试可以用
            if ("test".equalsIgnoreCase(code)) {
                chain.doFilter(request, response);
                return;
            }
            String jSessionId = request.getSession().getId();
            String sysCode = String.valueOf(redisUtils.get(jSessionId));
            if (StringUtils.isEmpty(sysCode)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(ExceptionMessageConstants.IMAGE_VERIFICATION_CODE_EXPIRED_EXCEPTION));
                return;
            }
            redisUtils.del(jSessionId);
            if (StringUtils.isEmpty(code)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(ExceptionMessageConstants.IMAGE_VERIFICATION_CODE_IS_NULL_EXCEPTION));
                return;
            }
            if (!sysCode.equalsIgnoreCase(code)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(ExceptionMessageConstants.IMAGE_VERIFICATION_CODE_FAULT_EXCEPTION));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}

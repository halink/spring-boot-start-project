package com.halink.scaffold.config.springsecurity.filter;

import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.exception.CustomAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final RedisTemplate<String, String> redisTemplate;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    @Value("${login-api}")
    private String loginUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase(loginUrl, request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            String code = request.getParameter("image_code");
            // TODO 测试可以用
            if ("test".equalsIgnoreCase(code)) {
                chain.doFilter(request, response);
                return;
            }
            String jSessionId = request.getSession().getId();
            String sysCode = String.valueOf(redisTemplate.opsForValue().get(jSessionId));
            if (StringUtils.isEmpty(sysCode)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(MessageConstants.IMAGE_VERIFICATION_CODE_EXPIRED_EXCEPTION));
                return;
            }
            redisTemplate.delete(jSessionId);
            if (StringUtils.isEmpty(code)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(MessageConstants.IMAGE_VERIFICATION_CODE_IS_NULL_EXCEPTION));
                return;
            }
            if (!sysCode.equalsIgnoreCase(code)) {
                authenticationFailureHandler.onAuthenticationFailure(request, response,
                        CustomAuthenticationException.getInstance(MessageConstants.IMAGE_VERIFICATION_CODE_FAULT_EXCEPTION));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}

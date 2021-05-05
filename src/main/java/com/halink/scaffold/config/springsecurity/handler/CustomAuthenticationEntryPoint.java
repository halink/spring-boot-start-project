package com.halink.scaffold.config.springsecurity.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有登录
 *
 * @author halink
 * @date 2019/11/27 11:17 上午
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ExceptionResult.builder().errorCode(CodeConstants.NO_LOGIN_IN).errorMessage(MessageConstants.NO_LOGIN_IN).build()
                )
        );
    }
}


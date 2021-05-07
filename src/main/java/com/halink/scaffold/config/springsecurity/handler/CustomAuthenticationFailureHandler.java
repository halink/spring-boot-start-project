package com.halink.scaffold.config.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.exception.CustomAuthenticationException;
import com.halink.scaffold.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败
 *
 * @author halink
 * @date 2019/11/27 11:18 上午
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String message;
        if (e instanceof CustomAuthenticationException) {
            message = e.getLocalizedMessage();
        } else {
            message = MessageConstants.LOGIN_FAILURE;
        }
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ExceptionResult.builder().errorCode(CodeConstants.UNAUTHORIZED).errorMessage(message).build()
                )
        );
    }
}

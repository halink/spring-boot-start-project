package com.halink.scaffold.config.springsecurity.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限
 *
 * @author halink
 * @date 2019/11/27 11:16 上午
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ExceptionResult.builder().errorCode(CodeConstants.FORBIDDEN).errorMessage(MessageConstants.FORBIDDEN).build()
                )
        );
    }
}


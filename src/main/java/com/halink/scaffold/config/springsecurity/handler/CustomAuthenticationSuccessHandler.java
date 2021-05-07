package com.halink.scaffold.config.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halink.scaffold.common.vo.user.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 *
 * @author halink
 * @date 2019/11/27 11:18 上午
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Object principal = authentication.getPrincipal();
        UserVo userVo = new UserVo();
        // TODO 改为MapStruct处理
        BeanUtils.copyProperties(principal, userVo);
        //自定义login，不走这里、若使用security的formLogin则自己添加业务实现(生成token、存储token等等)
        response.getWriter().write(
                objectMapper.writeValueAsString(userVo)
        );
    }
}

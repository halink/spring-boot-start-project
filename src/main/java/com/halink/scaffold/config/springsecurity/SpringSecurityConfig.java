package com.halink.scaffold.config.springsecurity;


import com.halink.scaffold.config.springsecurity.filter.ValidateCodeFilter;
import com.halink.scaffold.config.springsecurity.handler.*;
import com.halink.scaffold.config.springsecurity.service.MyUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * EnableGlobalMethodSecurity(prePostEnabled = true)
 * 表示开启全局方法注解，可在指定方法上面添加注解指定权限，需含有指定权限才可调用(基于表达式的权限控制)
 *
 * @author halink
 * @date 2019/11/27 11:15 上午
 */
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PASS_URL = {
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**",
            "/api/verification-code/**",
            "/actuator/**",
            "/index",
            "/druid/**"
    };
    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
     */
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 登录成功返回的 JSON 格式数据给前端（否则为 html）
     */
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 登录失败返回的 JSON 格式数据给前端（否则为 html）
     */
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
     */
    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
     */
    private final CustomAccessDeniedHandler accessDeniedHandler;
    /**
     * 自定义user
     */
    private final MyUserDetailsServiceImpl userDetailsService;
    /**
     * 验证码
     */
    private final ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 指定自定义userService验证，指定密码加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(PASS_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin().loginProcessingUrl("/api/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                //异常处理
                .exceptionHandling()
                //拒绝访问
                .accessDeniedHandler(accessDeniedHandler)
                //认证失败，没有登录
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .cors()
                .and().csrf().disable();
    }
}

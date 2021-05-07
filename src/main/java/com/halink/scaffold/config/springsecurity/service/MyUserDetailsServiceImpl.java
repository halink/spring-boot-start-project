package com.halink.scaffold.config.springsecurity.service;

import com.google.common.collect.Sets;
import com.halink.scaffold.common.dto.security.SecurityUser;
import com.halink.scaffold.common.entity.User;
import com.halink.scaffold.modular.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 登录验证
 *
 * @author halink
 * @date 2019/11/27 11:41 上午
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    /**
     * 若使用security表单鉴权则需实现该方法，通过username获取用户信息（密码、权限等等）
     *
     * @param username username
     * @return userDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            log.error("username not found! username:{}", username);
            // 这里抛出的异常security会处理成BadCredentialsException: Bad credentials
            throw new UsernameNotFoundException("username not found");
        }
        // TODO 模拟接口权限
        Set<GrantedAuthority> authoritiesSet = Sets.newHashSet();
        authoritiesSet.add(new SimpleGrantedAuthority("test:list"));
        authoritiesSet.add(new SimpleGrantedAuthority("test:add"));
        return SecurityUser.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authoritiesSet).build();
    }
}

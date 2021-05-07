package com.halink.scaffold.config.springsecurity.service;

import com.google.common.collect.Sets;
import com.halink.scaffold.common.entity.SysUser;
import com.halink.scaffold.modular.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    private final SysUserMapper userMapper;

    /**
     * 若使用security表单鉴权则需实现该方法，通过username获取用户信息（密码、权限等等）
     *
     * @param username username
     * @return userDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userMapper.selectByPrimaryKey(1);
        if (user == null) {
            log.error("username not found! username:{}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<SimpleGrantedAuthority> authoritiesSet = Sets.newHashSet();
        // 模拟从数据库中获取用户权限
        authoritiesSet.add(new SimpleGrantedAuthority("test:list"));
        authoritiesSet.add(new SimpleGrantedAuthority("test:add"));
        return new User(user.getUsername(), user.getPassword(), authoritiesSet);
    }
}

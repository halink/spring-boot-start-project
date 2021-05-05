package com.halink.scaffold.modular.controller;

import com.halink.scaffold.common.entity.User;
import com.halink.scaffold.common.enumerate.UserStatuesEnum;
import com.halink.scaffold.common.vo.user.UserVo;
import com.halink.scaffold.modular.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author halink
 * @date 2019/11/26 5:04 下午
 */
@Api(tags = "测试")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final UserMapper userMapper;

    @PostMapping("/login")
    @ApiOperation("测试接口")
    public UserVo login(@Valid @RequestBody UserVo userVo) {
        System.out.println(userVo);
        User user = User.builder().status(UserStatuesEnum.NORMAL).password("$2a$10$5Am5J8RDnBslVJje.C89S.jAPC2k98Zc4sRO2E2epQxuCtb5mZ9Xe")
                .lastLoginTime(userVo.getLastLoginTime())
                .build();
        userMapper.insertSelective(user);
        User user1 = userMapper.selectByPrimaryKey(user.getUserId());
        return UserVo.builder().status(UserStatuesEnum.NORMAL).lastLoginTime(user1.getLastLoginTime()).build();
    }
}

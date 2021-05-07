package com.halink.scaffold.modular.controller;

import com.halink.scaffold.common.entity.User;
import com.halink.scaffold.common.vo.user.UserVo;
import com.halink.scaffold.core.mapstruct.UserTransformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/index")
public class IndexController {

    private final UserTransformMapper userTransformMapper;

    @GetMapping()
    public UserVo index() {
        User user = User.builder()
                .username("user").password("123").userId(1).nickName("fsd").build();
        return userTransformMapper.toUserVO(user);
    }
}

package com.halink.scaffold;

import com.halink.scaffold.common.entity.SysUser;
import com.halink.scaffold.common.vo.user.UserVo;
import com.halink.scaffold.core.mapstruct.UserTransformMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
class StartProjectApplicationTests {
    @Autowired
    MessageSource messageSource;

    @Autowired
    UserTransformMapper userTransformMapper;

    @Test
    void contextLoads() {
        String car_exceed_limit = messageSource.getMessage("car_exceed_limit", new Object[]{3}, null);
        System.out.println(car_exceed_limit);
    }

    @Test
    void testMapstruct() {
        SysUser build = SysUser.builder().username("username").password("pass").build();
        UserVo userDto = userTransformMapper.toUserVO(build);
        System.out.println(userDto.toString());
    }
}

package com.halink.scaffold;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
class StartProjectApplicationTests {
    @Autowired
    MessageSource messageSource;

    @Test
    void contextLoads() {
        String car_exceed_limit = messageSource.getMessage("car_exceed_limit", new Object[]{3}, null);
        System.out.println(car_exceed_limit);
    }

}

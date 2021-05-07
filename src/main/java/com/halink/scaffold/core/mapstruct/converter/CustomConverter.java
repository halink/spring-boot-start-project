package com.halink.scaffold.core.mapstruct.converter;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * 自定义转换方法
 *
 * @author Halink
 * @date 2021-05-05 22:30
 */
@Slf4j
@Component
@Named("customConverter")
public class CustomConverter {

//    /**
//     * 例子
//     */
//    @Named("standardPic")
//    public String standardPic(String pic) {
//        return PicUtils.standardPicUrl(pic);
//    }

}

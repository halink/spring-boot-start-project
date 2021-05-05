package com.halink.scaffold.common.exception;

import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import org.springframework.http.HttpStatus;

/**
 * 接收枚举数据异常
 *
 * @author halink
 * @date 2020/9/27 6:49 下午
 */
public class AcceptEnumFormatException extends BaseException {
    public AcceptEnumFormatException() {
        super(HttpStatus.BAD_REQUEST, CodeConstants.ACCEPT_ENUM_FORMAT_EXCEPTION, MessageConstants.ACCEPT_ENUM_FORMAT_EXCEPTION);
    }
}

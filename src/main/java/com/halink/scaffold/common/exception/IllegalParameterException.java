package com.halink.scaffold.common.exception;

import com.halink.scaffold.common.constant.CodeConstants;
import org.springframework.http.HttpStatus;

/**
 * 参数错误异常
 *
 * @author Halink
 * @date 2021-05-05 19:57
 */
public class IllegalParameterException extends BaseException {
    public IllegalParameterException(String errorMsg, int errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode, errorMsg);
    }

    public IllegalParameterException(String errorMsg) {
        super(HttpStatus.BAD_REQUEST, CodeConstants.PARAMETER_EXCEPTION, errorMsg);
    }
}

package com.halink.scaffold.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 验证码异常
 *
 * @author halink
 * @date 2020/9/29 3:07 下午
 */
public class VerificationCodeException extends BaseException {
    public VerificationCodeException(int errorCode, String errorMsg) {
        super(HttpStatus.BAD_REQUEST, errorCode, errorMsg);
    }
}

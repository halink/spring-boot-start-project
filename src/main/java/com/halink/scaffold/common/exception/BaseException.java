package com.halink.scaffold.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * @author halink
 */
@Getter
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 424646336423235612L;
    /**
     * http状态码
     */
    private final HttpStatus status;
    /**
     * 错误码
     */
    private final int errorCode;
    /**
     * 错误信息
     */
    private final String errorMsg;
}

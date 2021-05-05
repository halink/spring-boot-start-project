package com.halink.scaffold.config.globalexception;

import com.halink.scaffold.common.constant.CodeConstants;
import com.halink.scaffold.common.constant.MessageConstants;
import com.halink.scaffold.common.exception.BaseException;
import com.halink.scaffold.common.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;

/**
 * 全局异常处理
 *
 * @author halink
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealException(HttpServletRequest req, Throwable e) {
        log.error("system error - ", e);
        ResponseEntity<ExceptionResult> responseEntity;
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            responseEntity = ResponseEntity.status(baseException.getStatus()).body(
                    ExceptionResult.builder().errorCode(baseException.getErrorCode())
                            .errorMessage(baseException.getErrorMsg()).build()
            );
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ExceptionResult.builder().errorCode(CodeConstants.SERVER_ERROR)
                            .errorMessage(MessageConstants.SERVER_ERROR).build()
            );

        }
        return responseEntity;
    }


    /**
     * 添加参数校验的Exception处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Param Error -> ", e);
        StringBuilder errorString = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorString.append(fieldName).append(":").append(errorMessage).append("! ");
            errorMsg.append(errorMessage).append("! ");
        });
        log.error("Param Error -> {}", errorString);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResult.builder().errorCode(CodeConstants.PARAMETER_EXCEPTION)
                        .errorMessage(errorMsg.toString()).build()
        );
    }

    /**
     * 添加参数校验的BindException处理
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealBindException(BindException e) {
        log.error("Param Error -> ", e);
        StringBuilder errorString = new StringBuilder();
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorString.append(fieldName).append(":").append(errorMessage).append("! ");
            errorMsg.append(errorMessage).append("! ");
        });
        log.error("Param Error -> {}", errorString);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResult.builder().errorCode(CodeConstants.PARAMETER_EXCEPTION)
                        .errorMessage(errorMsg.toString()).build()
        );
    }

    /**
     * 添加参数校验的ConstraintViolationException处理
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealConstraintViolationException(ConstraintViolationException e) {
        log.error("Param Error -> ", e);
        StringBuilder errorMsg = new StringBuilder();
        String[] msgs = e.getMessage().split(", ");
        for (String msg : msgs) {
            String[] fieldAndMsg = msg.split(": ");
            String message = fieldAndMsg[1];
            errorMsg.append(message).append("! ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResult.builder().errorCode(CodeConstants.PARAMETER_EXCEPTION)
                        .errorMessage(errorMsg.toString()).build()
        );
    }


    /**
     * 请求体丢失异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("请求体异常 Error -> ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResult.builder().errorCode(CodeConstants.REQUEST_BODY_EXCEPTION)
                        .errorMessage(MessageConstants.REQUEST_BODY_EXCEPTION).build()
        );
    }

    /**
     * 请求METHOD异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> dealHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("请求体异常 Error -> ", e);
        String[] supportedMethods = e.getSupportedMethods();
        String errorMessage;
        if (null == supportedMethods || supportedMethods.length == 0) {
            errorMessage = "请求方法未知,请联系管理员";
        } else {
            errorMessage = "请使用" + Arrays.toString(supportedMethods) + "方法";
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                ExceptionResult.builder().errorCode(CodeConstants.REQUEST_METHOD_EXCEPTION)
                        .errorMessage(errorMessage).build()
        );
    }

}

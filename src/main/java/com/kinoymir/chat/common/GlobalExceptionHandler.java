package com.kinoymir.chat.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局处理所有运行时异常
 *
 * @author kino
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数:{}", e.getMessage());
        return new JsonResult().failed("缺少请求参数");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败:{}", e.getMessage());
        return new JsonResult().failed("参数解析失败");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String code = error.getDefaultMessage();
        String message = String.format("%s", code);
        return new JsonResult().failed(message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JsonResult handleBindException(BindException e) {
        logger.error("参数绑定失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String code = error.getDefaultMessage();
        String message = String.format("%s", code);
        return new JsonResult().failed(message);
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return new JsonResult().failed(message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public JsonResult handleValidationException(ValidationException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        return new JsonResult().failed("参数验证失败");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法:{}", e.getMessage());
        return new JsonResult().failed("不支持当前请求方法");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResult handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型:{}", e.getMessage());
        return new JsonResult().failed("不支持当前媒体类型");
    }

    /**
     * 500 - Internal Server Error
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ChatRuntimeException.class)
    @ResponseBody
    public JsonResult handleServiceException(ChatRuntimeException e) {
        logger.error("业务逻辑异常 :{}", e.getMessage());
        JsonResult jr = new JsonResult().failed(e.getMessage());
        return jr;
    }

    /**
     * 500 - Internal Server Error
     */
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        logger.error("通用异常 :{}", e);
        JsonResult jr = new JsonResult().failed(e.getMessage());
        return jr;
    }

}

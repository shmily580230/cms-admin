package com.mm.common.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mm.common.util.R;
import com.mm.common.util.RCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理器
 *
 * @author lwl
 */
@Slf4j
@RestControllerAdvice
public class GExceptionHandler {

    @ExceptionHandler(BindException.class)
    public R<String> handleException(BindException e) {
        String msg = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return R.code(RCode.PARAM_FAILED, msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> handleException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return R.code(RCode.PARAM_FAILED, msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> handleException(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream().map(m -> m.getMessage()).collect(Collectors.toList()).get(0);
        return R.code(RCode.PARAM_FAILED, msg);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(GException.class)
    public R<String> handleRRException(GException e) {
        return R.code(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }
}

package com.duck.integration.config;

import com.duck.integration.common.DuckException;
import com.duck.integration.common.DuckResponse;
import com.duck.integration.common.ErrorCode;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@Hidden
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(DuckException.class)
    public DuckResponse<Void> handleBizException(DuckException e) {
        log.warn("业务异常: code={}, msg={}", e.getCode(), e.getMessage());
        return DuckResponse.fail(e.getCode(), e.getMessage());
    }

    /**
     * 参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public DuckResponse<Void> handleMissingParam(MissingServletRequestParameterException e) {
        log.warn("参数缺失: {}", e.getMessage());
        return DuckResponse.fail(ErrorCode.PARAM_ERROR.getCode(), "缺少参数：" + e.getParameterName());
    }

    /**
     * JSON 解析失败、Body 为空
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public DuckResponse<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("请求体解析失败: {}", e.getMessage());
        return DuckResponse.fail(ErrorCode.PARAM_ERROR.getCode(), "请求体格式错误");
    }

    /**
     * @Valid / @Validated 的方法参数校验失败（对象参数）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DuckResponse<Void> handleMethodArgNotValid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .orElse("参数校验失败");
        log.warn("参数校验失败: {}", msg);
        return DuckResponse.fail(ErrorCode.PARAM_ERROR.getCode(), msg);
    }

    /**
     * form 表单绑定错误
     */
    @ExceptionHandler(BindException.class)
    public DuckResponse<Void> handleBindException(BindException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .orElse("参数绑定失败");
        log.warn("参数绑定失败: {}", msg);
        return DuckResponse.fail(ErrorCode.PARAM_ERROR.getCode(), msg);
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public DuckResponse<Void> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return DuckResponse.fail(ErrorCode.SYSTEM_ERROR);
    }
}

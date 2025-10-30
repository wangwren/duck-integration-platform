package com.duck.integration.common;

import java.io.Serializable;

public class DuckResponse<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 业务码，000000 表示成功，其他表示失败
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 具体数据
     */
    private T data;

    public DuckResponse() {
    }

    private DuckResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /* ---------- 静态构建方法 ---------- */

    public static <T> DuckResponse<T> success(T data) {
        return new DuckResponse<>(true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public static <T> DuckResponse<T> success() {
        return new DuckResponse<>(true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), null);
    }

    public static <T> DuckResponse<T> fail(String code, String message) {
        return new DuckResponse<>(false, code, message, null);
    }

    public static <T> DuckResponse<T> fail(ErrorCode errorCode) {
        return new DuckResponse<>(false, errorCode.getCode(), errorCode.getMsg(), null);
    }

    public static <T> DuckResponse<T> fail(ErrorCode errorCode, String detailMsg) {
        return new DuckResponse<>(false, errorCode.getCode(), detailMsg, null);
    }

    /* ---------- getter / setter ---------- */

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.duck.integration.common;

public class DuckException extends RuntimeException {

    private final String code;

    public DuckException(String code, String message) {
        super(message);
        this.code = code;
    }

    public DuckException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public DuckException(ErrorCode errorCode, String detailMsg) {
        super(detailMsg);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}

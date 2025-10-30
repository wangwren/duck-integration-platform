package com.duck.integration.common;

public enum ErrorCode {

    SUCCESS("000000", "成功"),
    // --- 客户端问题 ---
    PARAM_ERROR("A00001", "参数校验失败"),
    NOT_FOUND("A00004", "资源不存在"),
    // --- 业务问题 ---
    BIZ_ERROR("B00001", "业务处理失败"),
    // --- 系统问题 ---
    SYSTEM_ERROR("S00001", "系统异常，请稍后重试"),
    DB_ERROR("S00002", "数据库访问异常"),
    REMOTE_ERROR("S00003", "远程服务调用失败");

    private final String code;
    private final String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

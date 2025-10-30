package com.duck.integration.controller;

import com.duck.integration.common.DuckException;
import com.duck.integration.common.DuckResponse;
import com.duck.integration.common.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试代码")
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String health() {
        return "ok";
    }

    @Operation(summary = "ping测试请求")
    @GetMapping("/ping")
    public DuckResponse<String> ping() {
        return DuckResponse.success("pong");
    }

    @Operation(summary = "error测试请求")
    @GetMapping("/error")
    public DuckResponse<Void> error() {
        // 这里模拟一个业务报错
        throw new DuckException(ErrorCode.BIZ_ERROR, "这个接口联调配置不存在");
    }
}

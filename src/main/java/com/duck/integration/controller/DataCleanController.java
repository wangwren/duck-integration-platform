package com.duck.integration.controller;

import com.duck.integration.common.DuckResponse;
import com.duck.integration.model.DataCleanRequest;
import com.duck.integration.service.DataCleanService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DataCleanController {

    @Resource
    private DataCleanService dataCleanService;

    @PostMapping("/data/clean")
    public DuckResponse<Void> clean(@RequestBody @Validated DataCleanRequest request) {
        dataCleanService.clean(request);
        return DuckResponse.success();
    }
}

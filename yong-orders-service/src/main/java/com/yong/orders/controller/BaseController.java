package com.yong.orders.controller;

import com.yong.orders.common.Result;
import com.yong.orders.common.ResultCode;
import com.yong.orders.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yong.a.liang on 6/22/2017.
 */

public class BaseController<T> {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private BaseService<T> service;

    public BaseController(BaseService<T> service) {
        this.service = service;
    }

    @PostMapping
    Result addOne(@RequestBody T instance) {
        try {
            return Result.success(service.addOne(instance));
        } catch (Exception err) {
            logger.error("addOne error", err);
            return Result.fail(ResultCode.FAIL, err.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    Result deactivate(@PathVariable String id) {
        try {
            service.delete(id);
            return Result.success();
        } catch (Exception err) {
            logger.error("deactivate error", err);
            return Result.fail(ResultCode.FAIL, err.getMessage());
        }
    }

    @GetMapping
    public Result findAll() {
        try {
            logger.debug("start findAll.");
            return Result.success(service.findAll());
        } catch (Exception err) {
            logger.error("findAll error", err);
            return Result.fail(ResultCode.FAIL, err.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable String id) {
        try {
            return Result.success(service.getOne(id));
        } catch (Exception err) {
            logger.error("getOne error", err);
            return Result.fail(ResultCode.FAIL, err.getMessage());
        }
    }

    @PutMapping
    Result updateOne(@RequestBody T instance) {
        try {
            return Result.success(service.updateOne(instance));
        } catch (Exception err) {
            logger.error("updateOne error", err);
            return Result.fail(ResultCode.FAIL, err.getMessage());
        }
    }
}
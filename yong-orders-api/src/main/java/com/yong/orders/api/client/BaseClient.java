package com.yong.orders.api.client;

import com.yong.orders.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/5
 * 抽取通用的增删改查方法
 */
public interface BaseClient<T> {

	@PostMapping
	Result<T> addOne(@RequestBody T instance);

	@DeleteMapping("/{id}")
	Result<T> delete(@PathVariable("id") String id);

	@GetMapping
	Result<List<T>> findAll();

	@GetMapping("/{id}")
	Result<T> getOne(@PathVariable("id") String id);

	@PutMapping
	Result<T> updateOne(@RequestBody T instance);
}
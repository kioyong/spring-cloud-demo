package com.yong.orders.api.controller;

import com.yong.orders.api.client.BaseClient;
import com.yong.orders.common.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/5
 */
@AllArgsConstructor
public class BaseController<T> {

	private final BaseClient client;

	@PostMapping
	Result<T> addOne(@RequestBody T instance,
					 HttpServletResponse response) {

		return client.addOne(instance);
	}


	@DeleteMapping("/{id}")
	public Result<T> delete(@PathVariable("id") String id,
							HttpServletResponse response) {

		return client.delete(id);
	}


	@GetMapping
	public Result<List<T>> findtAll(
		HttpServletResponse  response) {
		return client.findAll();
	}


	@GetMapping("/{id}")
	public Result<T> getOne(@PathVariable("id") String id,
							HttpServletResponse  response) {

		return client.getOne(id);
	}


	@PutMapping("")
	Result<T> updateOne(@RequestBody T instance,
						HttpServletResponse response) {

		return client.updateOne(instance);
	}
}

package com.yong.orders.api.client;

import com.yong.orders.common.Result;
import com.yong.orders.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/5
 * Fiegn Client Test Demo
 */
@FeignClient(name = "yong-orders", path = "/user")
public interface UserClient extends BaseClient<User>{

	@GetMapping("/departmentGroup/{departmentGroupId}")
	Result<List<User>> findUserByDepartmentGroup(@PathVariable String departmentGroupId);
}

package com.yong.orders.controller;

import com.yong.orders.common.Result;
import com.yong.orders.model.Address;
import com.yong.orders.model.User;
import com.yong.orders.service.AddressService;
import com.yong.orders.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yong.a.liang on 6/22/2017.
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User>{
    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    @Autowired
    private AddressService addService;

    @Autowired
    public UserController(UserService service){
        super(service);
        this.service = service;
    }

    @GetMapping("/departmentGroup/{departmentGroupId}")
    public Result<List<User>> findUserByDepartmentGroup(@PathVariable String departmentGroupId){
        return service.findUserByDepartmentGroup(departmentGroupId);
    }

    @GetMapping("/departmentGroupHashMapAll")
    public Map<String,List<String>> findUserByDepartmentGroupAll(){
        return service.findUserByDepartmentGroupAll();
    }
    @GetMapping("/departmentGroupHashMap")
    public Iterator<Map.Entry<String,String>> findUserByDepartmentGroup(){
        return service.findUserByDepartmentGroupMap();
    }

    @GetMapping("/location/{locationId}")
    public Result<List<User>> findByLocation(@PathVariable String locationId){
        List<Address> list = addService.findByLocation(locationId);
        List<String> addList = list.stream().map(a -> a.getAdd()).collect(Collectors.toList());
        List<User> result = service.findByAddressId(addList);
        return Result.success(result);
    }

    @GetMapping("/address/{addressId}")
    public Result<List<User>> findByAddressId(@PathVariable String addressId){
        List<User> byAddressId = service.findByAddressId(addressId);
        return Result.success(byAddressId);
    }

}
package com.yong.orders.service;

import com.yong.orders.common.Result;
import com.yong.orders.model.Address;
import com.yong.orders.model.User;

import java.util.*;

/**
 * Created by yong.a.liang on 6/21/2017.
 */


public interface UserService extends BaseService<User>{
    Result<List<User>> findUserByDepartmentGroup(String departmentGroupId);
    Map<String,List<String>> findUserByDepartmentGroupAll();
    Iterator<Map.Entry<String,String>> findUserByDepartmentGroupMap();
    List<User> findByAddress(Address address);
    List<User> findByAddressId(List<String> id);
    List<User> findByAddressId(String id);
    List<User> findByAddressLocation(String location);


}

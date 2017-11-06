package com.yong.orders.dao;

import com.yong.orders.model.Address;
import com.yong.orders.model.DepartmentGroup;
import com.yong.orders.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by yong.a.liang on 6/20/2017.
 */
public interface UserDao extends BaseDao<User>{

    List<User> findByDepartmentGroupList (List<DepartmentGroup> departmentGroupsList);

    User findByName(String name);

    List<User> findByAddress(Address address);
    List<User> findByAddress(List<Address> address);
    List<User> findByAddressAddIn(List<String> ids);
    List<User> findByAddressAdd(String id);
    List<User> findByAddressLocation(String location);

}

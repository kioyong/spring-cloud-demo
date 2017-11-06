package com.yong.orders.service.impl;

import com.yong.orders.common.Result;
import com.yong.orders.constant.SequenceKeys;
import com.yong.orders.dao.DepartmentGroupDao;
import com.yong.orders.dao.SequenceDao;
import com.yong.orders.dao.UserDao;
import com.yong.orders.dao.UserSnapshotDao;
import com.yong.orders.model.Address;
import com.yong.orders.model.DepartmentGroup;
import com.yong.orders.model.User;
import com.yong.orders.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yong.a.liang on 6/21/2017.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao dao;

    @Autowired
    private DepartmentGroupDao departmentGroupDao;

    @Autowired
    private UserSnapshotDao userSnapshotDao;

    @Autowired
    private SequenceDao sequenceDao;

    public UserServiceImpl(UserDao dao){
        super(dao);
        this.dao=dao;
    }
    @Override
    public User beforeAdd(User instance) {
        log.debug("Start set Sequence Keys");
        instance.setId(sequenceDao.getNextSequenceId(SequenceKeys.USER)+"");
        return instance;
    }


    @Override
    public User addOne(User user) {
        log.debug("Start User Save method.");
        doSaveUserSnapshot(user);
        try {
            validate(user);
            User instanceCopy = beforeAdd(user);
            instanceCopy.setCreatedBy(getCreatedBy());
            instanceCopy.setCreatedDate(new Date());
            instanceCopy.setIsActive(true);
            doSaveForAdd(instanceCopy);
            return instanceCopy;
        } catch (Exception err) {
            log.error("BaseServiceImpl::addOne: ", err);
            throw new IllegalArgumentException(err.getMessage());
        }
    }



    public void doSaveUserSnapshot(User user){
//        UserSnapshot user1 = new UserSnapshot();
//        BeanUtils.copyProperties(user,user1);
//        userSnapshotDao.save(user1);
    }

    @Override
    public Result<List<User>> findUserByDepartmentGroup(String departmentGroupId) {
        DepartmentGroup departmentGroup = departmentGroupDao.findById(departmentGroupId).get();
        List<DepartmentGroup> departmentGroupList=new ArrayList<DepartmentGroup>();
        departmentGroupList.add(departmentGroup);
        List<User> userList = dao.findByDepartmentGroupList(departmentGroupList);
        return Result.success(userList);
    }

    @Override
    public Iterator<Map.Entry<String,String>> findUserByDepartmentGroupMap(){
        List<User> userList = dao.findAll();
        Map<String,String> map =new IdentityHashMap<String,String>();
        for(User user:userList){
            List<DepartmentGroup> departmentGroupList = user.getDepartmentGroupList();
                for(DepartmentGroup departmentGroup : departmentGroupList){
                    map.put(departmentGroup.getId(),user.getName());
                }
        }
        Set<Map.Entry<String,String>> allSet = null ;   // 准备使用Set接收全部内容
        allSet = map.entrySet() ;
        Iterator<Map.Entry<String,String>> iter = null ;
        iter = allSet.iterator() ;
        return iter;
    }

    @Override
    public List<User> findByAddress(Address address) {
        List<User> byAddress = dao.findByAddress(address);
        return byAddress;
    }

    @Override
    public List<User> findByAddressId(List<String> ids) {
        return dao.findByAddressAddIn(ids);
    }

    @Override
    public List<User> findByAddressId(String id) {
        return dao.findByAddressAdd(id);
    }

    @Override
    public List<User> findByAddressLocation(String location) {
        return dao.findByAddressLocation(location);
    }


    @Override
    public Map<String,List<String>> findUserByDepartmentGroupAll(){
        List<User> userList = dao.findAll();
        Map<String,List<String>> hashMap =new HashMap<>();
        for(User user:userList){
            List<DepartmentGroup> departmentGroupList = user.getDepartmentGroupList();
            List<String> groupNameList = new ArrayList<String>();
            for(DepartmentGroup departmentGroup : departmentGroupList){
                groupNameList.add(departmentGroup.getName());
            }
            hashMap.put(user.getName(),groupNameList);
        }
        return hashMap;
    }

    public List<String> findAllDepartmentGroup(String groupName){
        List<User> userList = dao.findAll();
        List<String> result = new ArrayList<String>();
        for(User user : userList){
            List<DepartmentGroup> departmentGroupList = user.getDepartmentGroupList();
            for(DepartmentGroup departmentGroup : departmentGroupList){
                String name = departmentGroup.getName();
                if(name.equals(groupName)){
                    result.add(user.getName());
                    break;
                }
            }
        }
        return result;
    }

}

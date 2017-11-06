package com.yong.orders.dao;

import com.yong.orders.dao.UserDao;
import com.yong.orders.model.Address;
import com.yong.orders.model.DepartmentGroup;
import com.yong.orders.model.User;
import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiangYong on 2017/9/4.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
@Slf4j
public class UserDaoTests {

    @Autowired
    private UserDao dao;

    private User user;


    /**
     * create a User for unit Test
     *
     * **/
//    @BeforeClass
//    public static void setUpBeforeClass(){
//        log.info("before class start");
//    }

    @Before
    public void init(){
        log.info("before start");
        user = User.builder().age(20).id("123").name("testUser").address(
                null
        ).departmentGroupList(null).build();
        log.info("dao == null?{}",dao == null);
        user = dao.save(user);
    }

    @Test
    public void findByNameTest(){
        User result = dao.findByName("testUser");
        assertEquals(user,result);
    }


    /**
     * remove test case when test done.
     *
     * **/
//    @AfterClass
//    public static void tearDown (){
//        dao.deleteById(user.getId());
//    }
}

package com.yong.orders.demo;

import com.yong.orders.dao.UserDao;
import com.yong.orders.model.User;
import com.yong.orders.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

/**
 * Created by yong.a.liang on 8/8/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTests {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryIntegrationTests.class);

//    @Autowired
    @MockBean
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
//        log.debug("user count = {}",userDao.count());
//        userDao.deleteAll();
    }

    @After
    public void tearDown(){
//        userDao.deleteAll();
    }
//
//    @Test
//    public void findUserByname(){
//        User user = new User();
//        user.setName("testUser");
//        user.setAge(18);
//        user = userDao.save(user);
//        User result = userDao.findByName("testUser");
//        Assert.assertEquals(result.getAge(),18);
//        Assert.assertEquals(result.getName(),"testUser");
//        userDao.delete(user);
//    }

    @Test
    public void mockDaoTest(){
        List<User> list = new ArrayList<>();
        list.add(User.builder().age(20).name("testUser").build());
        given(this.userDao.findAll()).willReturn(list);
        List<User> all = userService.findAll();
        assertEquals(list,all);
    }


}


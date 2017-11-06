package com.yong.orders.demo;

import com.yong.orders.dao.UserDao;
import com.yong.orders.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by LiangYong on 2017/8/26.
 *   此test是为了学习Stream流而创建
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ListStreamTest {

//    private static final Logger log = LoggerFactory.getLogger(ListStreamTest.class);
//
//    public static void setUpBeforeClass(){
//        log.debug("start List Stream Test before Class !!!!!!!!!!!!!!!!");
//    }
//    @Autowired
//    private UserDao userDao;

//    @Test
//    public void testStream() throws Exception {
//        log.info("start test");
//        List<User> users = userDao.findAll();
//        Assert.assertNotEquals(users.size(),0);
//        Stream<User> distinctUsers = users.stream().distinct();
//        Assert.assertEquals(distinctUsers.count(),1);
////        List<User> result =
////        users.stream().filter(s ->{
////            return s.getAge()==22;
////        }).filter(s ->{
////            return !s.getName().equals("test4");
////        }).map(s ->{
////            s.setAge(s.getAge()+10);
////            log.debug("map userName:{}",s.getName());
////            return s;
////        })
////        .forEach(s ->{
////            result.add(s);
////            log.debug("add user name :{},user Age :{}",s.getName(),s.getAge());
////        });
////        .collect(Collectors.toList());
////        log.debug("result.size ={}",result.size());
//        // IntStrem 的使用
//        IntStream.range(1,3)
//                .mapToObj(i -> "a" + i)
//                .forEach(System.out::println);
//        log.info("end test");
//        Stream.of("a2","b3","c1","e7","d4","c3","d1")
//            .filter(s ->{
//                return s.startsWith("c")||s.startsWith("d");
//            })
//            .sorted((s1,s2) ->{
//                log.debug("s1 = {}, s2 = {}",s1,s2);
//                return s1.substring(1,2).compareTo(s2.substring(1,2));
//            }).map(s -> {
//                log.debug("upperCase : {}",s);
//                return s.toUpperCase();
//            }).forEach(System.out::println);
//        //测试按年龄分组
//        Map<Integer, List<User>> collect = users.stream()
//                .collect(Collectors.groupingBy(p -> p.getAge()));
//        log.debug("collect = {}",collect);
//        Assert.assertFalse(false);
//    }
}

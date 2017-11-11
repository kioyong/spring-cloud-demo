package com.yong.security.yongsecurity;

import com.yong.security.model.UserEntity;
import com.yong.security.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiangYong on 2017/10/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootWebFluxTest {

	@Autowired
	private UserDao userDao;

//	@Test
//	public void flatMapTest(){
//		/**
//		 * flatMap使用场景，在需要调用多个service的时候，且service没有优先顺序的时候，
//		 * key使用flatMap同步调用节省时间，类似于多线程call
//		 * **/
//		List<String> list = userDao.findAll()
//			.flatMap(t -> {
//				Mono<String> username = userDao.findById(t.getName()).map(r -> r.getName());//call service1
//				Mono<String> password = userDao.findById(t.getName()).map(r -> r.getPassword());//call service2
//				return username.zipWith(password, (name, pass) -> "name " + name + " has password " + pass + " .");
//			})
////			.collect(Collectors.toList()).block();
//			.collectList().block();//等价于上面的效果
//		for (String string:list) {
//			log.info(string);
//		}
//		Assert.assertTrue(true);
//	}




}


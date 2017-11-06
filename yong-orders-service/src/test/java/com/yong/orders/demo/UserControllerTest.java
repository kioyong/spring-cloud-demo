package com.yong.orders.demo;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * Created by yong.a.liang on 8/4/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment =DEFINED_PORT)
public class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetUserById(){
        //TODO due data not prepare. pending fixed use memory MongoDB .
        Assert.assertTrue(true);
//        String aaa="sdfsdf,dddd";
//        String split = aaa.split(",")[0];
//        log.debug(split);
//        Result<User> forObject = testRestTemplate.getForObject("/user/123", Result.class);//body
//        log.debug("Result = {} ",forObject.toString());
//        Assert.assertNotNull(forObject.getPayload());
//        Assert.assertEquals(0,forObject.getCode());
//        forObject = testRestTemplate.getForObject("/user/invalidId", Result.class);
//        Assert.assertNotNull(forObject.getMessage());
//        Assert.assertNotEquals(0,forObject.getCode());


//        testRestTemplate.put
//        ResponseEntity<Map> forEntity = testRestTemplate.getForEntity("usedfr/123", Map.class);//xml
//        testRestTemplate.get
//        log.debug("Result = {} ",forEntity.toString());
//        Assert.assertTrue(true);
//        @SuppressWarnings("rawtypes")
//        ResponseEntity<Map> entity = testRestTemplate.getForEntity("/eureka/apps", Map.class);
//        log.debug("result = {}",entity.toString());
//        assertEquals(HttpStatus.OK, entity.getStatusCode())
    }
}

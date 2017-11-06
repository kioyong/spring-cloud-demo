package com.yong.orders.demo;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/**
 * Created by LiangYong on 2017/9/3.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment =DEFINED_PORT)

@SpringBootTest
public class Junit4StudyTest {

    private static final Logger log = LoggerFactory.getLogger(Junit4StudyTest.class);


    @BeforeClass
    public static void setUpBeforeClass(){
        log.debug("test beforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass(){
        log.debug("test AfterClass");
    }

    @After
    public  void tearDown(){
        log.debug("test After");
    }

    @Before
    public void setUp(){
        log.debug("start test steUp");
    }

    @Test
    public void demoTest(){
        //disable
        log.debug("start demo Test");
        assertEquals(2,1+1);

    }

    @Test(expected =Exception.class)
    public void demoTest2() throws Exception {
        log.debug("start demo 2 test");
        int i = factorial(-1);
        fail("负数没有阶乘");
    }

    @Test(timeout =3000)
    public void timeoutTest() throws InterruptedException {
        Thread.sleep(2000);
    }
//    public int fibonacci(int n){
//        if (n == 1) return 0;
//        else if (n == 2) return 1;
//        else return fibonacci(n-1) + fibonacci(n-2);
//    }

    public int factorial(int n) throws Exception {
        if (n < 0) throw new Exception("负数没有阶乘");
        else if (n == 1) return 1;
        else return n * factorial(n - 1);
    }

    @Test
    public void assertTest(){
        String nullString = null;
        assertNull(nullString);
        String string = "null point test";
        assertNotNull(string);
        assertFalse(false);
        assertTrue(true);
        Integer i1 =127;
        Integer i2 = 127;
        log.debug("i1==i2?{}",i1==i2);
        Integer ii1 = new Integer(127);
        assertEquals(i1,ii1);
        assertNotSame(i1,ii1);
        assertEquals(i1,i2);
        assertSame(i1,i2);
        Integer i3 = 128;
        Integer i4 = 128;
        assertEquals(i3,i4);
        assertNotSame(i3,i4);
    }

//    @Test
//    public void failTest(){
//        int i = 1;
//        fail("test fail result");
//        log.debug("i == {}",i);
//    }

}

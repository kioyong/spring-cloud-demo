package com.yong.orders.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

/**
 * Created by LiangYong on 2017/9/3.
 */

@RunWith(Parameterized.class)
public class EvenNumberTest {

    Logger log = LoggerFactory.getLogger(EvenNumberTest.class);

    private int number;
    private String text;

    public EvenNumberTest(int number, String text) {
        this.number = number;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data = new Object[][]{
                {1,"test1"},
                {2,"test5"},
                {3,"test6"},
                {4,"test7"},
        };
        return Arrays.asList(data);
    }

    @Test
    public void EventParametersTest(){
        log.debug("this.number = {},this.text = {}",number,text);

        if(number<=2){
            assertTrue(true);
        }else{
            assertFalse(false);
        }

    }
}

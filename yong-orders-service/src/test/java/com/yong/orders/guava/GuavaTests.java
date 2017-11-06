package com.yong.orders.guava;

import com.google.common.base.Optional;
import static org.junit.Assert.*;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Throwables;
import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yong.a.liang on 9/7/2017.
 */
@Slf4j
public class GuavaTests {

    @Test(expected = NullPointerException.class)
    public void OptionalTest()throws NullPointerException{
        Integer value1 = null;
        Integer value2 = Integer.valueOf(10);
        //fromNullable allows passed parameter to be null
        Optional<Integer> optional1 = Optional.fromNullable(value1);
//        throws nullpointerException if passed parameter is null
        Optional<Integer> optional2 = Optional.of(value1);
        assertEquals(Integer.valueOf(10),sum(optional1,optional2));
        assertEquals(10,sum(optional1,optional2).intValue());
        assertEquals(Integer.valueOf(10),badSum(value1,value2));
        if(!optional1.isPresent()){
            fail();
        }
    }



    @Test(expected = IllegalArgumentException.class)
    public void checkArgumentTest(){
        //sqrt 平方根
        Double a = -4.0;
        double sqrt = sqrt(a);
        assertEquals(2.0,sqrt,0);

    }

    @Test(expected = NullPointerException.class)
    public void checkNotNullTest(){
        Integer a = null;
        checkNotNull(a);
        fail("not return IllegalArgumentException!");
    }

    @Test
    public void orderingTest(){
        List<Integer> list1 = Stream.generate(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return  (int) (Math.random() * 10);
            }
        }).limit(10).collect(Collectors.toList());
        List<Integer> list2 = IntStream.rangeClosed(1, 10).map(i -> (int) (Math.random() * 10)).boxed().collect(Collectors.toList());
        log.debug("before list 1  = {}",list1);
        log.debug("before list 2 = {}",list2);
        Collections.sort(list1, Ordering.natural());
        Collections.sort(list2, Ordering.natural().reversed());
        log.debug("after list 1  = {}",list1);
        log.debug("after list 2  = {}",list2);
        assertTrue(true);

    }

    @Test
    public void throwablesTest(){
        try {
            double sqrt = sqrt(-4.0);
        }catch(Exception e){
            log.debug("root Cause = {}",Throwables.getRootCause(e).toString() );
            log.debug("getStackTraceAsString = {}",Throwables.getStackTraceAsString(e).toString());
        }
        log.debug("end");

    }

    public Integer sum(Optional<Integer> a,Optional<Integer>b){
        Integer value1 = a.or(Integer.valueOf(0));
        return value1+b.get();
    }

    public Integer badSum(Integer a,Integer b){
        return a+b;
    }


    public double sqrt(double input) throws IllegalArgumentException{
        checkArgument(input > 0.0,"Illegal Argument passed: Negative value %s.",input);
        return Math.sqrt(input);
    }

}

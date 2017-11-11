package com.yong.security.yongsecurity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiangYong on 2017/10/17.
 */
public class NullPointStreamListTest {

    protected class Person{
        private List<Address> address;
        Person(List<Address> address){
            this.address=address;
        }
        private List<Address> getAddress(){
            if(this.address == null){
                return new ArrayList<>();
            }
            return this.address;
        }
    }
    protected class Address{
        private List<String> someMessage;

        Address(List<String> someMessage){
            this.someMessage=someMessage;
        }

        private List<String> getSomeMessage(){
            if(this.someMessage == null){
                return new ArrayList<>();
            }
            return this.someMessage;
        }
    }

    @Test
    public void doTest(){
        List<String> list1 = Arrays.asList("foo","bar","other");
        List<String> list2 = Arrays.asList("foo1","bar1","other1");
        Address add1 = new Address(list1);
        Address add2 = new Address(list2);
        Address add3 = new Address(new ArrayList<>());
        Address add4 = null;
        Person person1 = new Person(Arrays.asList(add1,add3,add4));
        List<String> collect = person1.getAddress().stream()
                .filter(s -> s != null)
                .flatMap(s -> s.getSomeMessage().stream())
                .collect(Collectors.toList());

    }
}

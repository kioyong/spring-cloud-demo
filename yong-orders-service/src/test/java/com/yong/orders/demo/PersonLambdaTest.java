package com.yong.orders.demo;

import com.yong.orders.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by LiangYong on 2017/8/31.
 */

public class PersonLambdaTest {

    Logger log = LoggerFactory.getLogger(PersonLambdaTest.class);

    @Test
    public void pringPersonTest(){
        System.out.print("start test");
        Assert.assertTrue(true);
        Person p1 = new Person();
        p1.setAge(20);
        p1.setNamue("Liangyong");
        Person p2 = new Person();
        p2.setAge(30);
        p2.setNamue("Tester");
        Person p3 = new Person();
        p3.setAge(40);
        p3.setNamue("ZhangShan");
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        List<Person> people = printPersonsOlderThan(persons, 20);
        Assert.assertEquals(1,people.size());
        Assert.assertEquals(p1,people.get(0));
    }

    public List<Person> printPersonsOlderThan(List<Person> persons,int age){
        List<Person> list = new ArrayList<>();
        for(Person person :persons){
            if(person.getAge()<=age){
                list.add(person);
            }
        }
        return list;
    }

    @Test
    public void streamTrainingTest(){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));

        // JDK 7 发现type为grocery的所有交易
        List<Transaction> result = new ArrayList<>();
        for(Transaction transaction : transactions){
            if(transaction.getType().equals(Transaction.Type.GEOCERY)){
                result.add(transaction);
            }
        }
        Assert.assertEquals(4,result.size());
        // 集合排序 交易值降序排序
        Collections.sort(result, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        log.debug(result.toString());
        log.debug("value = {} ",result.get(0).getValue());
        Integer value = result.get(0).getValue();
        Integer i = new Integer(60);
        Assert.assertEquals(value,i);


        //交易id 获取
        List<Integer> ids = new ArrayList<Integer>();
        for(Transaction transaction :result){
            ids.add(transaction.getId());
        }
        log.debug("id = {}",ids);

        List<Integer> collects = transactions.parallelStream()
                .filter(t -> t.getType() == Transaction.Type.GEOCERY)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId)
                .collect(Collectors.toList());
        log.debug("use JDK1.8, get ids = {}",collects);
        Assert.assertEquals(ids,collects);

    }

    @Test
    public void WidgetStreamTest(){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(new Widget(Color.RED, 1));
        widgets.add(new Widget(Color.RED, 2));
        widgets.add(new Widget(Color.BLACK, 3));
        widgets.add(new Widget(Color.BLUE, 4));
        // stream() 获取当前的source, filter 和 mapToInt为intermediate操作, 进行数据筛选和转换,
        // 最后一个sum为terminal操作，对符合条件的全部widget做重量求和
        int sum = widgets.stream()
                .filter(w -> w.getColor() == Color.RED)
                .mapToInt(Widget::getWeight)
//                .mapToInt(w -> w.getWeight()) //  与上面写法同样效果
                .sum();
        Assert.assertEquals(3,sum);
    }

    @Test
    public void MarkStreamTest(){
        // 1. Individual values 单独值
        Stream stream = Stream.of("a1", "b1", "c1");
        stream.forEach(System.out::print);//打印 a1b1c1
        System.out.println("");
        // 2. Arrays 数组
        String[] strArray = new String[] {"a2", "b2", "c2"};
        stream = Stream.of(strArray);
        stream.forEach(System.out::print);
        System.out.println("");
        stream = Arrays.stream(strArray);
//        System.out.println(stream.collect(Collectors.joining(",")).toString());//打印 a2,b2,c2

        // 3. Collections 集合
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

    }

    @Test
    public void UpperTest(){
        List<String> list = new ArrayList<>();
        list.add("stream");
        list.add("java8");
        list.add("test case");

        List<String> result = list.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        log.debug("result = {}",result);
        Assert.assertEquals("STREAM",result.get(0));

        List<String> result1 = list.stream()
                .map(s -> {
                    String s1 = s.substring(1, 4);
                    return s1.toUpperCase();
                })
                .collect(Collectors.toList());
        log.debug("result1 = {}",result1);
        List<String> result2 = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        log.debug("result2 = {}",result2);
    }

    @Test
    public void toSquareTest() {
        //返回1到10 的平方数
        int[] ints = IntStream.rangeClosed(1, 10)
                .map(n -> n * n)
                .toArray();
        log.debug("ints ={}",ints);

        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> result = list.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        log.debug("result = {}", result);

        //留下偶数
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result1 = list2.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        log.debug("result1 = {}", result1);
        log.debug("user.dir = {}",System.getProperty("user.dir"));
        }


    @Test
    public void nullPointTest(){
        String text1 = "HuangGuiXia";
        String text2 = "  ";
        String text3 = null;
        String text4 = text2.trim();
        String text5 = "";
        log.debug("text1.length = {}",text1.length());
        log.debug("text2.length = {}",text2.length());
        log.debug("text4.length = {}",text4.length());
        log.debug("text5.length = {}",text5.length());

        //java 7
        if(text3 != null){
            log.debug("text3.length = {}",text3.length());
        }
        Assert.assertEquals(text4,text5);
        Assert.assertTrue(true);
        Integer text3Length = Optional.ofNullable(text3).map(String::length).orElse(-1);
        Optional.ofNullable(text3).ifPresent(System.out::println);
        Assert.assertEquals(new Integer(-1),text3Length);

    }

    @Test
    public void reducelTest(){
        //求和sum10
        int sum = 0;
        for(int i=1;i<=10;i++){
            sum += i;
        }
        Assert.assertEquals(55,sum
        );
        //java8
        int result = IntStream.rangeClosed(1, 10)
                .reduce(0, (a, b) -> a + b);//identity 有无起始值，也可无此参数
        Assert.assertEquals(55,result);
        int result1 = IntStream.rangeClosed(1, 10)
                .reduce(Integer::sum)
                .getAsInt();
        Assert.assertEquals(55,result1);
        //求最小值
        //java7
        int result2;
        List<Integer> integers = Arrays.asList(2, 3, 5,1,7, 8, 9);
        for(int i=0;i<integers.size()-1;i++){
            if(integers.get(i).compareTo(integers.get(i+1))>0){
                result = integers.get(i+1);
            }
        }
        Assert.assertEquals(result,1);
        Integer integer = integers.stream().reduce(Integer::min).get();
        Assert.assertEquals(Integer.valueOf(1),integer);
        //求list<String>最长长度的字符串
        List<String> stringList = Arrays.asList("hello","i am liangyong","give me fine","just for myself");
        Integer integer1 = stringList.stream()
                .map(s -> s.length())
                .reduce(Integer::max)
                .get();
        Assert.assertEquals(Integer.valueOf(15),integer1);
        List<String> collect = stringList.stream()
                .filter(s -> s.length() == integer1.intValue())
                .collect(Collectors.toList());
        String s = collect.get(0);
        log.debug("max length String = {}",s);
        Assert.assertEquals(s,"just for myself");

        int asInt = stringList.stream().mapToInt(String::length).max().getAsInt();
        String s1 = stringList.stream().filter(t -> t.length() == asInt).findFirst().get();
        Assert.assertEquals(s,s1);

    }

    @Test
    public void integgerTest(){
        Integer i1 = 127;
        Integer i2 =127;
        Assert.assertEquals(i1,i2);
        Integer i3 = 128;
        Integer i4 =128;
        log.debug("i1==i2?,{}",i1==i2);
        log.debug("i3==i4?,{}",i3==i4);
        Integer i6 =Integer.valueOf(100);
        Assert.assertEquals(i3,i4);
        Integer ii1 = new Integer(127);
        int i=127;
        Integer integer = Integer.valueOf(i);
        integer.toString();
        Assert.assertEquals(i1,ii1);
        log.debug("i1==ii1?,{}",i1==ii1);
        log.debug("i1.equals(ii1)? {}",i1.equals(ii1));

    }

    @Test
    public void sequenceTest(){
        //产生一个从1开始，等差为3 的等差数列，数量为20个
        int[] ints = IntStream.rangeClosed(1, 20)
                .map(n -> n * 3)
                .toArray();
        log.debug("ints = {}",ints);
        Assert.assertTrue(true);

        //test String
        String string = "Hello My name Is Liangyong what Is Your Name";
        List<String> collect = Arrays.stream(string.split(" "))
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        log.debug("String textWord = {}",collect);
    }

    @Test
    public void groupingTest(){
        List<User> users = new ArrayList<>();
        users.add(User.builder().age(5).name("test1").build());
        users.add(User.builder().age(5).name("test2").build());
        users.add(User.builder().age(10).name("test3").build());
        users.add(User.builder().age(10).name("test4").build());
        users.add(User.builder().age(15).name("test5").build());
        users.add(User.builder().age(15).name("test6").build());
        users.add(User.builder().age(20).name("test7").build());
        users.add(User.builder().age(21).name("test8").build());
        // 按年龄分组
        Collection<List<User>> values = users.stream()
                .collect(Collectors.groupingByConcurrent(User::getAge))
                .values();//并发执行，所以循序可能不一样
        Collection<List<User>> values1 = users.stream()
                .collect(Collectors.groupingBy(s -> s.getAge()))
                .values();
        log.debug("collect = {}",values);
//        Assert.assertEquals(values,values1); false

        //按成年、未成年分组
        Map<Boolean, List<User>> collect = users.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 18));
        log.debug("collect = {}",collect);

    }
}
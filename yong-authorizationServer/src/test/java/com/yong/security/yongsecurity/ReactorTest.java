package com.yong.security.yongsecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @acthor yong.a.liang
 * @createdDate 10/17/2017
 */
@Slf4j
public class ReactorTest {

    private static List<String> words = Arrays.asList(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog"
    );

    /**
     * 打印出每一个单词
     * **/
    @Test
    public void simpleCreation(){
        Flux.fromIterable(words)
            .subscribe(System.out::println);
    }

    /**
     * 找出words里面缺失的英文字母,并尝试用Flux补全
     * 只有订阅(subscribe)后才会触发Flux的流,可以看到start subscribe 的log是最先输出的,
     * 类似于builder 的build() 方法
     * **/
    @Test
    public void findingMissingLetter(){
        Flux<String> flux = Flux.fromIterable(words)
                .flatMap(string -> {
                    log.debug("test");
                    return Flux.fromArray(string.split(""));
                })
                .concatWith(Mono.just("s"))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (t, count) -> String.format("%2d.%s", count, t));
//            .subscribe(System.out::println)
        log.debug("start subscribe");
        flux.subscribe(System.out::println);
    }


    /**
     * subscribe 的测试
     * 在主线程里对事件源进行订阅无法完成更加复杂的异步操作,主要是因为在订阅完成后,
     * 控制权会马上返回到主线程,例如没有下面的sleep的话,
     * hello world 在输出来之前,程序就已经退出
     */
    @Test
    public void shortCircuit(){
        log.debug("start test at time {}",new Date());
        Flux<String> helloPauseWorld = Mono.just("Hello")
                .concatWith(Mono.just(" World").delaySubscription(Duration.ofMillis(1000)));
        helloPauseWorld.subscribe(System.out::println);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("end test at time {}",new Date());
    }

    /**
     * toStream()将操作转到分响应模式完整输出了hello world
     * subscribe只输出了hello
     * **/
    @Test
    public void blocks(){
        Flux<String> helloPauseWorld = Mono.just("Hello")
                .concatWith(Mono.just("World").delaySubscription(Duration.ofMillis(1000)));
//        helloPauseWorld.toStream().forEach(System.out::println);
        helloPauseWorld.subscribe(System.out::println);
    }

    /**
     * 选择第一个Flux来触发,另一个将不会被触发
     * **/
//    @Test
//    public void firstEmitting(){
//        Mono<String> a = Mono.just("I'm late").delaySubscription(Duration.ofMillis(450));
//        Flux<String> b = Flux.just("let's get", "the party", "started").delaySubscription(Duration.ofMillis(400));
//        Flux.first(a,b).toStream().forEach(System.out::println);
//    }



}

package com.yong.security.yongsecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LiangYong on 2017/10/15.
 * 不需要启动Application，所以不需要添加@RunWith(SpringRunner.class) 和@SpringBootTest
 * 这种测试能够快速启动，适合某一个方法的测试+ Mock的方法 来实现method level的单元测试
 * */

@Slf4j
public class NonSpringBootWebFluxTest {
	@Test
	public void newFluxTest(){
		log.debug("start new Flux Test");
		//生成Flux
		Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);

		//生成Mono
		Mono<String> noData = Mono.empty();
		Mono<String> data = Mono.just("foo");
		//遍历 test1
		List<Integer> list = Flux.range(5, 3).collectList().block();
		log.debug("list = {}",list);
		//遍历 test2
		Flux.range(5,3).subscribe(i ->
			log.debug("i={}",i),
			error->log.error("have some error return!"),
			()->log.debug("done!"));

	}

	@Test
	public void StringFluxTest(){
		Flux<String> list = Flux.just("foo", "bar", "foobar","testString");
		list.map(String::toUpperCase).subscribe(
			t-> log.debug("string = {}",t)
		);
		Flux.generate(
			()->0,
			(state,sink)->{
				sink.next("3 x " + state + " = " + 3*state);
				if (state == 10) sink.complete();
				return state + 1;
			}
		).subscribe(
			t-> log.debug("{}",t)
		);
	}

	@Test
	public void schedulersTest(){
		Flux.range(1, 1000)
			.publishOn(Schedulers.parallel())
			.subscribe(
				i -> log.info("i = {}",i)
			);
	}
	@Test
	public void mapTest(){
		Mono.just(10)
			.map(this::doSomeThing)
			;
	}
	public int doSomeThing(int i){
		return i;
	}
}

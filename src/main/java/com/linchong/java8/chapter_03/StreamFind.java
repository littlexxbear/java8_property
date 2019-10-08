package com.linchong.java8.chapter_03;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-21 13:48
 * @Description:
 */
public class StreamFind {
	public static void main(String[] args) {
		Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

		Optional<Integer> optional1 = stream.filter(i -> i % 2 == 0).findAny();
		System.out.println(optional1.get());

		//error,none,No value present
		stream = Arrays.stream(new Integer [] {1,2,3,4,5,6,7});
		Optional<Integer> optional3 = stream.filter(i -> i < 10).findAny();

		//System.out.println(optional3.get());
		System.out.println(optional3.orElse(-1));


		stream = Arrays.stream(new Integer [] {1,2,3,4,5,6,7});
		Optional<Integer> optional2 = stream.filter(i -> i%2 == 0).findFirst();
		//存在，返回true;反之，false
		//optional2.isPresent();
		//ifPresent(Consumer)
		optional2.ifPresent(System.out::println);
		//filter再一次过滤，得到Functional
		System.out.println(optional2.filter(i->i==2).get());


		int result = find(new Integer[]{1, 2, 3, 4, 5, 6, 7}, -1, i -> i > 100);
		System.out.println(result);


		stream = Arrays.stream(new Integer [] {1,2,3,4,5,6,7});
		Optional<Integer> optional5 = stream.filter(i -> i>8).findFirst();
		System.out.println("=====");
		System.out.println(optional5.orElseGet(new customSupplier()));

	}

	//根据某个条件去找,满足条件的返回，未满足返回默认值
	private static int find(Integer [] values, int defdefaultValue, Predicate<Integer> predicate){
		for (Integer value : values) {
			if(predicate.test(value)){
				return value;
			}
		}
		return defdefaultValue;
	}


	static class customSupplier implements Supplier<Integer>{

		@Override
		public Integer get() {
			int i = new Random().nextInt(10);
			System.out.println("没有找到满足条件的值，返回一个默认值："+i);
			return i;
		}
	}
}



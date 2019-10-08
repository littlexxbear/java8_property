package com.linchong.java8.chapter_04;



import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_04
 * @Author:linchong
 * @CreateTime:2019-07-21 21:15
 * @Description:
 */

public class NumericStream {
	public static void main(String[] args) {
		//demo1();
		/*Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
		IntStream intStream = stream.mapToInt(Integer::intValue);
		int result= intStream.filter(i->i>3).sum();
		System.out.println(result);*/

		//int类型转为Integer
		//给定a=9,在1-1000范围内找到与a结合满足勾股定理的数，并放入到数组中result[1,2,3...]
		//类似切片，产生1-100的intStream
		int a = 9;
		/*IntStream intStream = IntStream.rangeClosed(1, 100)
				.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0);
		intStream.forEach(System.out::println);*/
		//boxed装箱为对象，利用对象
		//map(Function<? super T, ? extends R> mapper)
		IntStream.rangeClosed(1, 100)
				.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
				.boxed().map(x->new int[]{a,x,(int)Math.sqrt(a*a+x*x)})
				.forEach(r->System.out.println("a="+r[0]+", b="+r[1]+", c="+r[2]));

		System.out.println("==================================================");
		IntStream.rangeClosed(1, 100)
				.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
				.mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
				.forEach(r->System.out.println("a="+r[0]+", b="+r[1]+", c="+r[2]));





	}

	private static void demo1() {
		Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
		//filter > 3,and get sum
		//Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);
		//integerStream.reduce(0,Integer::sum);
		//等价于
		//Integer result = stream.filter(i -> i.intValue() > 3).reduce(0, Integer::sum);
		//等价于
		//stream.filter(i -> i.intValue() > 3).reduce(Integer::sum).ifPresent(System.out::println);
		//Integer的内存占用量大，int(4byte/32bit)，比Integer内存占用量小，java提供了IntegerStream,方便使用拆箱后的数据

		//将Integer转为int,使用mapToInt,需要传入参数ToIntFunction
		/**
		 * Function:<R T> R apply(T t);
		 * Supplier:<T> T get();
		 * Consummer:void accept(T t);
		 * Predicate:boolean test(T t);
		 */
		//Integer result = stream.filter(i -> i.intValue() > 3).reduce(0, Integer::sum);
		/**
		 * int applyAsInt(T value)
		 *
		 * int applyAsInt(T value)
		 *
		 * 传入一个，返回int类型的
		 */

		IntStream intStream = stream.mapToInt(Integer::intValue);
		int sum = intStream.filter(i -> i > 3).sum();
		System.out.println(sum);
		//或者
		/*int reduce = intStream.filter(i -> i > 3).reduce(0, (a, b) -> a + b);
		System.out.println(reduce);*/
	}
}

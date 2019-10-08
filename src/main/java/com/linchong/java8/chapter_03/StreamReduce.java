package com.linchong.java8.chapter_03;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-21 15:50
 * @Description: 类似元素的聚合操作，传入参数BiFunction,terminate类型的操作
 *                  reduce,聚合作用根据你传入的Function进行对应的操作
 */
public class StreamReduce {
	public static void main(String[] args) {
		Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

		//打印出所有元素和     T reduce(T identity, BinaryOperator<T> accumulator);
		// BinaryOperator extends BiFuncational
		//0是初始值，0+1+2+3+...+7
		//Integer result = stream.reduce(0, (i, j) -> i + j);
		Integer result = stream.reduce(0, Integer::sum);
		System.out.println(result);

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//计算，如果结果存在，输出它，Optional<T> reduce(BinaryOperator<T> accumulator);
		stream.reduce((i, j) -> i + j).ifPresent(System.out::println);

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		/*stream.reduce((i, j) -> {
			return i > j ? i : j;
		}).ifPresent(System.out::println);*/
		stream.reduce(Integer::max).ifPresent(System.out::println);

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		stream.reduce((a,b)->a*b).ifPresent(System.out::println);

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//filter传出的是intexxxx,不会被中断
		stream.filter(i->i%2==0).reduce((a,b)->a*b).ifPresent(System.out::println);
		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//有初始值1
		Integer reduce = stream.filter(i -> i % 2 == 0).reduce(1, (a, b) -> a * b);
		System.out.println(reduce);
		//通过Optional继续操作
		Optional.of(reduce).ifPresent(System.out::println);
	}
}

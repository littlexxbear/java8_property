package com.linchong.java8.chapter_03;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-19 18:18
 * @Description: filter操作，传入一个Predicate<T>即可，boolean test(T t),true，进入下一个stream中，反之过滤掉
 */
public class StreamFilter {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,2,3,3,4,5,5,5,6);
		List<Integer> result = list.stream().filter(a -> a % 2 == 0).collect(toList());
		System.out.println(result);


		//去重
		result = list.stream().distinct().collect(toList());
		System.out.println(result);
		//元素截断,跳过前面元素
		result = list.stream().skip(5).collect(toList());
		System.out.println(result);
		//元素截断,取前面元素
		result = list.stream().limit(5).collect(toList());
		System.out.println(result);

		result = list.stream().limit(50).collect(toList());
		System.out.println(result);
	}
}

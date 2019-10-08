package com.linchong.java8.chapter_03;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-21 13:35
 * @Description:
 */
public class StreamMatch {
	public static void main(String[] args) {
		Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//都满足传入的Predicate条件，Predicate:boolean test(T t)
		boolean matched = stream.allMatch(i -> i>0);
		assert matched : "some elements not matched";

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//存在一个大于6
		matched = stream.anyMatch(integer -> integer>6);
		System.out.println(matched);

		stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
		//没有存在小于0的
		matched = stream.noneMatch(integer -> integer<0);
		System.out.println(matched);
	}
}

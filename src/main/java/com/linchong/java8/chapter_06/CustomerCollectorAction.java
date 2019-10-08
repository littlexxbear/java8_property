package com.linchong.java8.chapter_06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-23 10:52
 * @Description:自定义Collector测试
 */
public class CustomerCollectorAction {
	public static void main(String[] args) {
		Collector<String, List<String>, List<String>> collector =
				new ToListCollector<>();

		String[] arrs = new String[]{"PHP", "Java Web", "Hello", "Demo", "Python"};

		//转为stream,进行filter操作
		List<String> collect = Arrays.stream(arrs)
				.filter(s -> s.length() > 5)
				.collect(collector);

		System.out.println(collect);

		List<String> result = Arrays.asList("PHP", "Java Web", "Hello", "Demo", "Python")
				.parallelStream().filter(s -> s.length() >= 5)
				.collect(collector);
		System.out.println(result);
	}
}

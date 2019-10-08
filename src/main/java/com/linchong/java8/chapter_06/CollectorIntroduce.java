package com.linchong.java8.chapter_06;

import com.linchong.java8.chapter_01.Apple;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-22 15:54
 * @Description:
 */
public class CollectorIntroduce {
	public static void main(String[] args) {
		List<Apple> list = Arrays.asList(
				new Apple("green", 150),
				new Apple("yellow", 120),
				new Apple("green", 170),
				new Apple("green", 150),
				new Apple("yellow", 120),
				new Apple("green", 170));

		//聚合操作
		List<Apple> greenList = list.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
		Optional.ofNullable(greenList).ifPresent(System.out::println);

		//根据颜色，进行分组
		Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
		Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);


	}

	private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {
		Map<String, List<Apple>> map = new HashMap<>();
		for (Apple a : apples) {
			List<Apple> list = map.get(a.getColor());
			if (null == list) {
				list = new ArrayList<>();
				map.put(a.getColor(), list);
			}
			list.add(a);
		}
		return map;
	}

	private static Map<String,List<Apple>> groupByFunctionNormal(List<Apple> apples){
		Map<String,List<Apple>> map = new HashMap<>();
		for (Apple apple : apples) {
			List<Apple> list = new ArrayList<>();
			if(null==list){
				map.put(apple.getColor(),list);
			}
			list.add(apple);
		}
		return map;

	}



	private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
		Map<String, List<Apple>> map = new HashMap<>();
		apples.stream().forEach(a -> {
			List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
				List<Apple> list = new ArrayList<>();
				map.put(a.getColor(), list);
				return list;
			});
			colorList.add(a);
		});
		return map;
	}


	private static Map<String,List<Apple>> groupByCollector(List<Apple> appeles){
		return appeles.stream().collect(Collectors.groupingBy(Apple::getColor));
	}
}

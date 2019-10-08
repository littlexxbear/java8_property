package com.linchong.java8.chapter_03;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-10 13:44
 * @Description:Stream入门
 */
public class SimpleStream {
	public static void main(String[] args) {
		//菜中选择某一指定calories菜，并按照指定顺序排列输出
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH)
		);

		List<String> dishNamesByCollections = getDishNamesByCollections(menu);
		System.out.println(dishNamesByCollections);

		List<String> dishNamesByStream = getDishNamesByStream(menu);
		System.out.println(dishNamesByStream);

		//流的中断特性
		/*Stream<Dish> stream = menu.stream();
		stream.forEach(System.out::println);
		stream.forEach(System.out::println);*/

		List<String> result = menu.stream().filter(d -> {
			System.out.println("filtering->" + d.getName());
			return d.getCalories() > 300;
		}).map(d -> {
					System.out.println("map->" + d.getName());
					return d.getName();
		}).limit(3).collect(toList());
		Stream<Dish> stream = Stream.of(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER));
		stream.forEach(System.out::println);

	}

	private static List<String> getDishNamesByStream(List<Dish> menu) {
		//Predicate: boolean test(T t);
		return menu.stream().filter(d -> d.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName).collect(toList());
	}

	private static List<String> getDishNamesByCollections(List<Dish> menu) {
		List<Dish> lowCalories = new ArrayList<>();
		//filter and get calories less 400
		for (Dish d : lowCalories) {
			if (d.getCalories() < 400) {
				lowCalories.add(d);
			}
		}

		//sort
		Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
		/*Collections.sort(lowCalories,(a,b)->a.getName().compareTo(b.getName()));
		Collections.sort(lowCalories,Comparator.comparing(Dish::getName));*/
		List<String> dishNameList = new ArrayList<>();
		for (Dish d : lowCalories) {
			dishNameList.add(d.getName());
		}
		return dishNameList;
	}
}

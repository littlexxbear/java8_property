package com.linchong.java8.chapter_03;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-19 18:31
 * @Description: 传入Function, R apply(T t)
 */
public class StreamMap {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5, 5, 6);

		List<Integer> result = list.stream().map(a -> a * 2).collect(toList());

		System.out.println(result);
		//条件过滤，只返回name
		listDish().stream().map(a -> a.getName()).collect(toList()).forEach(System.out::println);

		//flatMap,flat(扁平化)，传入function,但是返回是必须是Stream类型的
		//去掉雷同的char
		String[] words = {"Hello","World"};
		//{H,e,l,l,o},{W,o,r,l,d}
		Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));//Stream<String[]>

		List<String[]> collect = Arrays.stream(words).map(w -> w.split("")).collect(toList());
		Arrays.stream(words).map(w -> w.split("")).collect(toList()).forEach(a-> Arrays.stream(a).forEach(System.out::print));
		/*for (String[] strings : collect) {
			for (String string : strings) {
				System.out.println(string);
			}
		}*/
		System.out.println("========");
		//扁平化处理，{H,e,l,l,o,W,o,r,l,d}
		Stream<String> stringStream = stream.flatMap(Arrays::stream);
		//去重，得到目标结果
		stringStream.distinct().forEach(System.out::println);
		List<Person> persons = Arrays.asList(
				Person.builder().name("zs").age(21).build(),
				Person.builder().name("ls").age(22).build(),
				Person.builder().name("ww").age(23).build(),
				Person.builder().name("zl").age(24).build(),
				Person.builder().name("wb").age(25).build()
		);

	}
	@Data
	@Builder
	static class Person{
		private String name;
		private Integer age;
	}



	public static List<Dish> listDish() {
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
		return menu;
	}
}

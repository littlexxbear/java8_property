package com.linchong.java8.chapter_06;

import com.linchong.java8.chapter_03.Dish;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.linchong.java8.chapter_06.CollectorsAction.menu;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-22 18:32
 * @Description:
 */
public class CollectorsAction3 {

	public static void main(String[] args) {
		testPartitioningByWithPredicate();
		testPartitioningByWithPredicateAndCollector();
		testReducingWithBinaryOperator();
		testReducingBinaryOperatorAndIdentiy();
		testReducingBinaryOperatorAndIdentiyAndFunction();
		testSummarizingDouble();
		testSummarizingInt();
		testSummarizingLong();
	}

	//根据是否是蔬菜对它们进行分组
	private static void testPartitioningByWithPredicate() {
		System.out.println("testPartitioningByWithPredicate");
		Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		Optional.of(map).ifPresent(System.out::println);
	}

	//根据是否是蔬菜对它们进行分组，分组完成后的交给后面的Collector处理
	//是否是蔬菜+平均的卡路里
	private static void testPartitioningByWithPredicateAndCollector() {
		System.out.println("testPartitioningByWithPredicate");
		Map<Boolean, Double> map = menu.stream()
				.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
		Optional.ofNullable(map).ifPresent(System.out::println);
	}

	//获取卡路里最大的dish
	//BinaryOperator是BiFunction的子接口，两个入参，一个出参
	private static void testReducingWithBinaryOperator() {
		System.out.println("testReducingWithBinaryOperator");
		menu.stream()
				.collect(
						Collectors.reducing(
										BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)))
				).ifPresent(System.out::println);
	}

	private static void testReducingBinaryOperatorAndIdentiy(){
		System.out.println("testReducingBinaryOperatorAndIdentiy");
		Integer result = menu.stream().map(Dish::getCalories)
				.collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
		System.out.println(result);
	}

	//map的位置
	private static void testReducingBinaryOperatorAndIdentiyAndFunction(){
		System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
		Integer result = menu.stream()
				.collect(Collectors.reducing(0,Dish::getCalories, (d1, d2) -> d1 + d2));
		System.out.println(result);
	}

	private static void testSummarizingDouble(){
		System.out.println("testSummarizingDouble");
		Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
		.ifPresent(System.out::println);
	}

	private static void testSummarizingLong(){
		System.out.println("testSummarizingLong");
		Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	private static void testSummarizingInt(){
		System.out.println("testSummarizingInt");
		Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}
}

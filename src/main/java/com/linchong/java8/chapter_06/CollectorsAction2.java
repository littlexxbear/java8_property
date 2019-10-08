package com.linchong.java8.chapter_06;
import com.linchong.java8.chapter_03.Dish;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-22 17:42
 * @Description:
 */
public class CollectorsAction2 {
	public static void main(String[] args) {
		testGroupingByConcurrentWithFunction();
		testGroupByConcurrentWithFunctionAndCollector();

		testGroupByConcurrentWithFunctionAndSupplierAndCollector();

		testJoining();
		testJoiningWithDelimiter();
		testJoiningWithDelimiterAndSuffix();
		testMapping();
		testMaxBy();
		testMinBy();
	}

	private static void testGroupingByConcurrentWithFunction(){
		System.out.println("testGroupingByConcurrentWithFunction");
		ConcurrentMap<Dish.Type, List<Dish>> collect = CollectorsAction.menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
		Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
		Optional.ofNullable(collect).ifPresent(System.out::println);
	}

	private static void testGroupByConcurrentWithFunctionAndCollector(){
		System.out.println("testGroupByConcurrentWithFunctionAndCollector");
		ConcurrentMap<Dish.Type,Double> collect =
				CollectorsAction.menu.stream()
						.collect(Collectors.groupingByConcurrent(Dish::getType,Collectors.averagingInt(Dish::getCalories)));

		Optional.ofNullable(collect).ifPresent(System.out::println);
	}

	private static void testGroupByConcurrentWithFunctionAndSupplierAndCollector(){
		System.out.println("testGroupByConcurrentWithFunctionAndSupplierAndCollector");
		ConcurrentMap<Dish.Type,Double> collect =
				CollectorsAction.menu.stream()
						.collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new,Collectors.averagingInt(Dish::getCalories)));

		Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
		Optional.ofNullable(collect).ifPresent(System.out::println);
	}

	private static void testJoining(){
		System.out.println("testJoining");
		Optional.of(CollectorsAction.menu.stream().map(Dish::getName).collect(Collectors.joining()))
		.ifPresent(System.out::println);
	}

	private static void testJoiningWithDelimiter(){
		System.out.println("testJoiningWithDelimiter");
		Optional.of(CollectorsAction.menu.stream().map(Dish::getName).collect(Collectors.joining("--")))
				.ifPresent(System.out::println);
	}

	private static void testJoiningWithDelimiterAndSuffix(){
		System.out.println("testJoiningWithDelimiterAndSuffix");
		Optional.of(CollectorsAction.menu.stream().map(Dish::getName).collect(Collectors.joining("--","start--","--end")))
				.ifPresent(System.out::println);
	}

	private static void testMapping(){
		System.out.println("testMapping");
		Optional.of(CollectorsAction.menu.stream().collect(Collectors.mapping(Dish::getName,
			Collectors.joining(",")))).ifPresent(System.out::println);
	}

	private static void testMaxBy(){
		System.out.println("testMapping");
	CollectorsAction.menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	private static void testMinBy(){
		System.out.println("testMinBy");
		CollectorsAction.menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}
}

package com.linchong.java8.chapter_06;

import com.linchong.java8.chapter_03.Dish;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

import java.util.stream.Collectors;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-22 16:26
 * @Description:
 */
public class CollectorsAction {
	public final static List<Dish> menu = Arrays.asList(
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

	public static void main(String[] args) {
		testAverageingDouble();
		testCollectionAndThen();
		testCounting();
		testGroupingByFunction();
		testGroupingByFunctionAndCollector();
		testGroupingByFunctionAndSupplierAndCollector();
		testSummarizingInt();
	}

	//输入reduce范畴
	private static void testAverageingDouble(){
		System.out.println("testAveragingDouble");
		Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
				.ifPresent(System.out::println);
	}


	private static void testAverageingInt(){
		System.out.println("testAveragingInt");
		Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	private static void testAverageingLong(){
		System.out.println("testAveragingLong");
		Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	//T:传入变量；A:累加器；R:出来值, downstream 交给Function的值，R入参，RR出参
	//CollectionAndThen
	//static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream,
	//                                                                Function<R,RR> finisher)
	//求平均数，并输出
	private static void testCollectionAndThen(){

		System.out.println("testCollectingAndThen");
		//聚合后的操作
		Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(Dish::getCalories),
				a -> "The Average Calories is ->" + a))).ifPresent(System.out::println);


		//操作肉类时，只返回数据，不允许操作
		List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList ));
		/*List<Dish> dishes = Collections.unmodifiableList(list);
		list.add(new Dish("",false,100, Dish.Type.OTHER));
		System.out.println(list);*/
	}

	//统计结果，long类型
	private static void testCounting(){
		System.out.println("testCounting");
		Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
	}

	//根据类型分类
	private static void testGroupingByFunction(){
		System.out.println("testGroupingByFunction");
		Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
	}
	//根据类型分类，再次基础上统计各个类型的数量
	private static void testGroupingByFunctionAndCollector(){
		System.out.println("testGroupingByFunctionAndCollector");
		Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting())))
				.ifPresent(System.out::println);

		//统计每个类型卡路里的平均值
		Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.averagingInt(Dish::getCalories))))
				.ifPresent(System.out::println);
	}

	//8. groupingBy(Function<? super T,? extends K> classifier,Supplier<M> mapFactory,Collector)
	private static void testGroupingByFunctionAndSupplierAndCollector(){
		System.out.println("testGroupingByFunctionAndSupplierAndCollector");
		Map<Dish.Type, Double> map = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,TreeMap::new
						,Collectors.averagingInt(Dish::getCalories)));

		Optional.of(map.getClass()).ifPresent(System.out::println);
		Optional.of(map).ifPresent(System.out::println);
	}

	private static void testSummarizingInt(){
		System.out.println("testSummarizingInt");
		IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		Optional.of(result).ifPresent(System.out::println);
	}
}

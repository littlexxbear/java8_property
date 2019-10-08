package com.linchong.java8.chapter_06;
import com.linchong.java8.chapter_03.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.linchong.java8.chapter_06.CollectorsAction.menu;
/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-22 19:25
 * @Description:
 */
public class CollectorsAction4 {
	public static void main(String[] args) {
		testSummingDouble();
		testToCollection();
		testToConcurrentMap();
		testToConcurrentMapWithBinaryOperator();
		testToConcurrentMapWithBinaryOperatorAndSupplier();
		testToList();
		testToSet();

		testToMap();
		testToMapWithBinaryOperator();
		testToMapWithBinaryOperatorAndSupplier();
	}

	private static void testSummingDouble(){
		System.out.println("testSummingDouble");
		Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
		.ifPresent(System.out::println);

		Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum()).ifPresent(System.out::println);
	}

	private static void testSummingLong(){
		System.out.println("testSummingLong");
		Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	private static void testSummingInt(){
		System.out.println("testSummingInt");
		Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
				.ifPresent(System.out::println);
	}

	private static void testToCollection(){
		System.out.println("testToCollection");
		Optional.of(menu.stream().filter(d->d.getCalories()>600)
				.collect(Collectors.toCollection(LinkedList::new)))
				.ifPresent(System.out::println);
	}

	private static void testToConcurrentMap(){
		System.out.println("testToConcurrentMap");
		//key:name,value:calories
		Optional.of(menu.stream()
				.collect(Collectors.toConcurrentMap(Dish::getName,Dish::getCalories)))
				.ifPresent(v->{
					System.out.println(v);
					System.out.println(v.getClass());
				});
	}

	//统计，Type:Total,Function:key,Function:value,BinaryOperator:statistic data
	private static void testToConcurrentMapWithBinaryOperator(){
		System.out.println("testToConcurrentMapWithBinaryOperator");
		Optional.of(menu.stream()
				.collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)->a+b)))
				.ifPresent(v->{
					System.out.println(v);
					System.out.println(v.getClass());
				});
	}

	//统计，Type:Total,Function:key,Function:value,BinaryOperator:statistic data
	//结果为ConcurrentSkipList
	private static void testToConcurrentMapWithBinaryOperatorAndSupplier(){
		System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
		Optional.of(menu.stream()
				.collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)->a+b, ConcurrentSkipListMap::new)))
				.ifPresent(v->{
					System.out.println(v);
					System.out.println(v.getClass());
				});
	}

	private static void testToList(){
		Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
		.ifPresent(r->{
			System.out.println(r.getClass());
			System.out.println(r);
		});
	}

	private static void testToSet(){
		Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
				.ifPresent(r->{
					System.out.println(r.getClass());
					System.out.println(r);
				});
	}

	private static void testToMap(){
		System.out.println("testToMap");

		//转为线程安全的
		Optional.of(menu.stream().collect(Collectors.collectingAndThen(
				Collectors.toMap(Dish::getName,Dish::getCalories),Collections::synchronizedMap)
		)).ifPresent(v->{
			System.out.println(v.getClass());
			System.out.println(v);
		});
		/*Optional.of(menu.stream()
		.collect(Collectors.toMap(Dish::getName,Dish::getCalories))).ifPresent(System.out::println);*/
	}

	private static void testToMapWithBinaryOperator(){
		System.out.println("testToMapWithBinaryOperator");
		Optional.of(menu.stream()
				.collect(Collectors.toMap(Dish::getType,v->1L,(a,b)->a+b)))
				.ifPresent(r->{
					System.out.println(r.getClass());
					System.out.println(r);
				});
	}

	private static void testToMapWithBinaryOperatorAndSupplier(){
		System.out.println("testToMapWithBinaryOperatorAndSupplier");
		Optional.of(menu.stream()
				.collect(Collectors.toMap(Dish::getType,v->1L,(a,b)->a+b, Hashtable::new)))
				.ifPresent(r->{
					System.out.println(r.getClass());
					System.out.println(r);
				});

	}
}

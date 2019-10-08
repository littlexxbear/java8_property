package com.linchong.java8.chapter_06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_06
 * @Author:linchong
 * @CreateTime:2019-07-23 10:34
 * @Description:自定义Collector
 */

/**
 * T:Stream元素的类型
 * A:要创建的Contrainer的类型
 * R:返回的数据类型
 *
 */
public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {

	private void log(final String log){
		System.out.println(Thread.currentThread().getName()+log);
	}

	//初始化时，调用Supplier产生一个指定类型的Contrainer
	@Override
	public Supplier<List<T>> supplier() {
		log("--Supplier");
		return ArrayList::new;
	}

	//将Contrainer中的值依次拿去消费 ，参数为一个Contrainer，一个其中的元素
	@Override //BiConsumer<T, U>
	public BiConsumer<List<T>, T> accumulator() {
		log("--BiConsumer");
		return List::add;
	}

	//BinaryFunction,两个值均为List<T>
	@Override
	public BinaryOperator<List<T>> combiner() {
		log("--Combiner");
		return (list1,list2)->{
			list1.addAll(list2);
			return list1;
		};
	}

	//最终返回的R,传入什么，返回什么
	@Override
	public Function<List<T>, List<T>> finisher() {
		//return Function.identity();
		log("--Finisher");
		return t->t;
	}

	//特征值
	@Override
	public Set<Characteristics> characteristics() {
		log("--Characteristics");
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT));
	}
}

package com.linchong.java8.chapter_02;

import com.linchong.java8.chapter_01.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.character_02
 * @Author:linchong
 * @CreateTime:2019-07-09 10:11
 * @Description:lambda表达式
 */
public class LambdaExpression {
	public static void main(String[] args) {
		//定义比较器，传入比较器进行比较
		Comparator<Apple> byColor = new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getColor().compareTo(o2.getColor());
			}
		};
		List<Apple> list = Collections.emptyList();
		list.sort(byColor);

		//引入lambda表达式优化
		//类型推导，可以不用写return
		Comparator<Apple> byColor2 = (o1,o2)->o1.getColor().compareTo(o2.getColor());
		//可以写return,  =(o1,o2) -> {return o1.getColor().compareTo(o2.getColor());};

		//lambda表达式举例：(String s) -> s.length();返回类型：Function
		Function<String, Integer> flambda = s -> s.length();
		//(Apple a) -> a.getColor().equals("green");返回类型：Predicate
		Predicate<Apple> appleFilter = a -> a.getColor().equals("green");
		//(int x,int y) -> { System.out.println(x); System.out.println(y);};
		//                          ||
		//-->public interface Test{ public void function(int x,int y);}
		Runnable r = () -> {};
		Test t = () -> { return "Hello Test"; };



	}
	public interface Test{
		public String fun();
	}
}

package com.linchong.java8.practise;

import com.linchong.java8.chapter_01.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.practise
 * @Author:linchong
 * @CreateTime:2019-09-15 13:15
 * @Description:
 */
public class lambdaPractise {

	public static List<Apple> filter(List<Apple> list, Predicate<Apple> predicate){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : list) {
			if(predicate.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<Apple> resource = Arrays.asList(new Apple("green",150),new Apple("red",140));
		List<Apple> result = filter(resource,a->a.getColor().equals("green"));
		for (Apple apple : result) {
			System.out.println(result);
		}
	}
}

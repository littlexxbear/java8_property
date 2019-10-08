package com.linchong.java8.chapter_01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8
 * @Author:linchong
 * @CreateTime:2019-07-07 22:12
 * @Description:需求一：筛选指定条件的苹果
 */

public class FilterApple {
	//需求一：删选出指定颜色的苹果
	public static List<Apple> findGreenApple(List<Apple> apples){
		List<Apple> list = new ArrayList<>();
		for (Apple apple : apples) {
			if("green".equals(apple.getColor())){
				list.add(apple);
			}
		}
		return list;
	}

	//需求二：筛选出颜色为红色的苹果，更改策略，采用条件进行过滤
	public static List findApple(List<Apple> apples,String color){
		List<Apple> list = new ArrayList<>();
		for (Apple apple : apples) {
			if(color.equals(apple.getColor())){
				list.add(apple);
			}
		}
		return list;
	}

	//需求三：根据苹果的各种条件筛选，如颜色和重量，利用策略模式
	public  interface AppleFilter{
		boolean filter(Apple apple);
	}

	public static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
		List<Apple> list = new ArrayList<>();
		for (Apple apple : apples) {
			if(appleFilter.filter(apple)){
				list.add(apple);
			}
		}
		return list;
	}

	//利用各种各样的过滤器去过滤
	public static class GreenAnd150Filter implements AppleFilter {
		@Override
		public boolean filter(Apple apple) {
			return "green".equals(apple.getColor())&&apple.getWeight()>=160;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<Apple> list = Arrays.asList(new Apple("green",150),new Apple("yellow",120),new Apple("green",170));
		//demo1(list);  //遍历集合去判断
		//demo2(list);  //传入条件
		//demo3(list);  //传入过滤器
		//demo4(list);  //利用匿名内部类，避免了多次实现指定接口的繁琐性,但是体积大
		//利用lambda表达式
		List<Apple> lambdaResult = findApple(list, (Apple apple) -> {
			return apple.getColor().equals("green");
		});
		System.out.println(lambdaResult);
		//上式可以简写，因为只有一个参数，它会推导它是apple类型的，只有一个参数，也可以把括号去掉
		List<Apple> lambdaResult2 = findApple(list, (apple) -> {
			return apple.getColor().equals("green");
		});
		//扩展--lambda表达式需要满足的条件：
			//有且只有一个方法，default和static方法除外，它会在编译的时候判断，可以不标注它为FunctionalInterface,类似的有Runner

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		thread.start();
		//主线程运行时可能已经结束了，没有执行到，所以利用join函数，阻塞主线程的运行
		Thread.currentThread().join(); //利用sleep也可以


		new Thread(()->System.out.println(Thread.currentThread().getName())).start();
		Thread.currentThread().join();

		//Comparator
	}


	private static void demo4(List<Apple> list) {
		List<Apple> yellowList = findApple(list, new AppleFilter() {
			@Override
			public boolean filter(Apple apple) {
				return "yellow".equals(apple.getColor());
			}
		});
		System.out.println(yellowList);
	}

	private static void demo3(List<Apple> list) {
		List<Apple> result = findApple(list,new GreenAnd150Filter());
		System.out.println(result);
	}

	private static void demo2(List<Apple> list) {
		List<Apple> greenApples = findApple(list,"green");
		System.out.println(greenApples);
	}

	//需求一测试
	private static void demo1(List<Apple> list) {
		List<Apple> greenApple = findGreenApple(list);
		assert greenApple.size() == 2 : "不符合预期";  //断言
		for (Apple apple : greenApple) {
			System.out.println(apple);
		}
	}

}

package com.linchong.java8.chapter_02;

import com.linchong.java8.chapter_01.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.character_02
 * @Author:linchong
 * @CreateTime:2019-07-09 14:51
 * @Description:lambda表达式的方法推导
 */
public class MethodReference {

	public static void main(String[] args) {

		Consumer<String> consumer = (s) -> System.out.println(s);
		useConsumber(consumer,"Hello World !");

		useConsumber(s -> System.out.println(s),"Hello World");

		useConsumber(System.out::println,"Hello World");

		//排序，通过color的ascii码
		List<Apple> list = Arrays.asList(new Apple("Green",110),
				new Apple("Red",123),
				new Apple("Yellow",180));
		System.out.println(list);

		list.sort((a1,a2) ->{
			return a1.getColor().compareTo(a2.getColor());});
		list.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));

		list.stream().forEach(a->System.out.println(a));
		list.stream().forEach(System.out::println);

	//什么时候需要函数推导
		list.stream().forEach(a -> System.out.println(a)); //循环遍历，将list中数据比遍历出来
		list.stream().forEach(System.out::println); //循环遍历，将list中数据比遍历出来

		//双冒号运算操作符是类方法的句柄,是lambda表达式的一种简写
		/**
		 *  x -> System.out.println(x) 简化为 System.out::println 的过程称之为 eta-conversion
		 *  System.out::println 简化为 x -> System.out.println(x) 的过程称之为 eta-expansion
		 *  举例：
		 *  person -> person.getAge();可以替换为Person::getAge
		 *  x -> System.out.println(x)可以替换成System.out::println
		 *
		 */

		/*BiFunction<String, Integer, Character> f2 = String::charAt;
		Character c = f2.apply("hello",3);
		System.out.println(c);*/
		//1.通过静态方法去创建：
		int value = Integer.parseInt("123");
		Function<String,Integer>  f = Integer::parseInt;
		Integer apply = f.apply("123");
		System.out.println(apply);

		//2.可以通过对象的一个实例方法去推断，如一个类方法
		//需要一个类的成员方法，需要传入这个类本身
		BiFunction<String,Integer,Character> f2 = String::charAt;
		Character c= f2.apply("hello",3);
		System.out.println(c);

		test t = new MethodReference().new test();
		BiFunction<test, Integer, Integer> getNumber = test::getNumber;
		int result = getNumber.apply(t,5);
		System.out.println(result);

		//3.可以使用对象存在一个实例方法去做取代
		String string = new String("hello");
		Function<Integer ,Character>  f3 =string::charAt;
		Character c1 = f3.apply(2);


		testFunction(string,a->a.length());
		System.out.println(c1);


		//方法推导部分
		//supplier<T>: T get(); one parameter
		Supplier<String> supplier = String::new;
		String s = supplier.get();
		System.out.println(s);

		//two parameters
		BiFunction<String,Long,Apple> appleBiFunction = Apple::new;
		Apple apple = appleBiFunction.apply("Red", 120l);
		System.out.println(apple);

		//three parameters
		ThreeFunction<String,String,Long,ComplexApple> appleThreeFunction = ComplexApple::new;
		ComplexApple complexApple = appleThreeFunction.apply("Red", "苹果", 120L);
		System.out.println(complexApple);


		List<Apple> list2 = Arrays.asList(new Apple("Green",110),
				new Apple("Red",123),
				new Apple("Yellow",180));
		list2.sort(Comparator.comparing(Apple::getColor));
	}

	private class test{
		public int getNumber(int n){
			return n+1;
		}
	}


	private static void testFunction(String str,Function<String,Integer> function){
		function.apply(str);
	}




	private  static <T> void useConsumber(Consumer<T> consumer,T t){
		consumer.accept(t);
		consumer.accept(t);
	}
}










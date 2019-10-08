package com.linchong.java8.chapter_02;

import com.linchong.java8.chapter_01.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.character_02
 * @Author:linchong
 * @CreateTime:2019-07-09 11:10
 * @Description:Lambda表达式的使用
 */
public class LambdaUsage {

	@FunctionalInterface
	public interface Adder{
		int add(int a,int b);
	};


	//@FunctionalInterface,不是，处理有父类的add抽象方法外，还有自己的重载的add方法实现
	public interface SmartAdder extends Adder{
		int  add(long a,long b);
	};

	@FunctionalInterface
	public interface Empty extends Adder{

	}

	@FunctionalInterface
	public interface DefaultAddr extends Adder{
		default int otherAddr(int a,int b){
			return a+b;
		}
	}

	public static void main(String[] args) {
		//Lambda表达式使用场景：
		//WhereToUsage();

		//How to Use:
		//由需求得到Function的用法：过滤指定颜色和重量的苹果
		List<Apple> list = Arrays.asList(new Apple("green",150),new Apple("yellow",120));
		List<Apple> greenList = filter(list, apple -> apple.getColor().equals("green"));
		System.out.println(greenList);
		//除Predicate<T>之外，还有细化的Predicate，如
		// BiPredicate<T,U>( boolean test(T t, U u);), IntPredicate(boolean test(int value);),
		// LongPredicate(boolean test(long value);), DoublePredicate(boolean test(double value);)
		List<Apple> weightList = filterByWeight(list,value -> value>100);
		System.out.println(weightList);

		List<Apple> result = filterByBiPredicate(list,(color,weight)->{return color.equals("green")&&weight>100;});
		List<Apple> result2 = filterByBiPredicate(list,(color,weight)->(color.equals("green")&&weight>100));
		System.out.println(result);
		System.out.println(result2);

		//Consumer<T> (void accept(T t);)
		//BiConsumer<T,U>(void accept(T,U);)
		simpleTestConsumer(list,a-> System.out.println(a));
		simpleBiConsumer(list,(a,b)-> System.out.println("颜色为："+a+"重量为："+b));
		simpleBiConsumer2(list,(a,b)-> System.out.println("信息为："+a.getWeight()+"颜色为："+a.getColor()));
		//Function:传入一个苹果，传出的苹果String
		Apple apple = Apple.builder().weight(120).color("red").build();
		String result3 = testFunction(apple, a -> a.toString());
		System.out.println(result3);

		String color = "红色";
		long weight = 105L;
		testBiFunction(color,weight,(c,w)->new Apple(c,w));

		compute(3,r -> r*r);
		compute(3,r -> r+r);

		//Function compose,compose是默认方法，接受一个function作为参数，将参数function的执行结果
		//作为参数给调用的function,以次实现两个function组合的功能
		int b = compute(2,r -> r*r,w -> w+w);  //(2+2)*(2+2) = 16

		//Function andThen,接收一个function作为参数，与compse不同的是，先执行本身的apply方法，
		// 将执行的结果作为参数给参数中的function。
		andThen(4,r -> r+1, w -> w*2);  //(4+1)*2 = 10

		IntFunction<Double> f = i -> i * 100d;
		Double result4 = f.apply(10);

		Supplier<String> s = String::new;
		System.out.println(s.getClass());

		createApple(() -> new Apple("green",120));

		int i = 0;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				//i++;
				System.out.println(i);
			}
		};
		Runnable  r2 = () -> System.out.println(i);
	}

	private static Apple createApple(Supplier<Apple> supplier){
		return supplier.get();
	}

	public static int andThen(int a,Function<Integer,Integer> function,Function<Integer,Integer> function2){
		return function.andThen(function2).apply(a);
	}
	private static int compute(int a,Function<Integer,Integer> function,Function<Integer,Integer> function2){
		return function.compose(function2).apply(a);
	}

	private static int compute(int a,Function<Integer,Integer> function){
		return function.apply(a);
	}
	//两个输入，一个输出
	private static Apple testBiFunction(String color,Long weight,BiFunction<String,Long,Apple> function){
		return function.apply(color,weight);
	}
	//一个传入->一个传出
	private static String testFunction(Apple apple,Function<Apple,String> function){
		return function.apply(apple);
	}


	private static void WhereToUsage() {
		Runnable r = () -> System.out.println(Thread.currentThread().getName());
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		};
		process(r);
		process(r2);
		process(()->{ System.out.println(Thread.currentThread().getName()); });
	}

	private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate){
		List<Apple> result =  new ArrayList<>();
		for (Apple apple : source) {
			if(predicate.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : source) {
			if(predicate.test(apple.getWeight())){
				result.add(apple);
			}
		}
		return result;
	}

	private static  List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Long> predicate){
		List<Apple> result = new ArrayList<>();
		for (Apple apple : source) {
			if(predicate.test(apple.getColor(),apple.getWeight())){
				result.add(apple);
			}
		}
		return result;
	}

	private static void simpleTestConsumer(List<Apple> source,Consumer<Apple> consumer){
		for (Apple apple : source) {
			consumer.accept(apple);
		}
	}
	private static void simpleBiConsumer(List<Apple> source,BiConsumer<String,Long> consumer){
		for (Apple apple : source) {
			consumer.accept(apple.getColor(),apple.getWeight());
		}
	}

	private static void simpleBiConsumer2(List<Apple> source,BiConsumer<Apple,String> consumer){
		for (Apple apple : source) {
			consumer.accept(apple,apple.getColor());
		}
	}
	private static void process(Runnable r){
		r.run();
	}

}




package com.linchong.java8.chapter_07;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_07
 * @Author:linchong
 * @CreateTime:2019-07-23 11:10
 * @Description:并行编程
 */
public class ParallelProcessing {
	public static void main(String[] args) {
		//设置并发数
		//System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","20");
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println("The best process time(normalAdd) >= "+meausurePerformance(ParallelProcessing::normalAdd,100_000_000)+"MS");

		//System.out.println("The best process time(interateStream) >= "+meausurePerformance(ParallelProcessing::interateStream,10000000)+"MS");
		//System.out.println("The best process time(parallelStream) >= "+meausurePerformance(ParallelProcessing::parallelStream,10000000)+"MS");
		//System.out.println("The best process time(parallelStream2) >= "+meausurePerformance(ParallelProcessing::parallelStream2,10000000)+"MS");

		System.out.println("The best process time(parallelStream3) >= "+meausurePerformance(ParallelProcessing::parallelStream3,100_000_000)+"MS");

		/*
		* 适合并行处理的数据结构
		* ArrayList:Excellent
		* LinkedLIst:Poor
		* IntStream.range:Excellent
		* Stream.iterate:Poor
		* HashSet:Good
		* TreeSet:Good
		*
		* */
	}


	private static long meausurePerformance(Function<Long,Long> adder,long limit){
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long startTimeStamp = System.currentTimeMillis();
			long result = adder.apply(limit);
			long duration = System.currentTimeMillis()-startTimeStamp;
			//System.out.println("The result of sum = "+result);
			if(duration<fastest) fastest = duration;
		}
		return fastest;
	}
	//1-n的加法累加运算，并行运算
	//种子，一元运算符
	//public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
	private static long interateStream(long limit){
		return Stream.iterate(1L,i->i+1).limit(limit).reduce(0L,Long::sum);
	}

	//每次都需要拆箱和装箱
	private static long parallelStream(long limit){
		return Stream.iterate(1L,i->i+1).parallel()
				.limit(limit).reduce(0L,Long::sum);
	}

	private static long parallelStream2(long limit){
		return Stream.iterate(1L,i->i+1).mapToLong(Long::longValue).parallel()
				.limit(limit).reduce(0L,Long::sum);
	}

	private static long parallelStream3(long limit){
		return LongStream.rangeClosed(1,limit).parallel().reduce(0L,Long::sum);
	}

	private static long normalAdd(long limit){
		long result = 0L;
		for (int i = 0; i < limit; i++) {
			result +=i;
		}
		return result;
	}
}

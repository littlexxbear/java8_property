package com.linchong.java8.chapter_08;

import java.util.concurrent.ForkJoinPool;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_08
 * @Author:linchong
 * @CreateTime:2019-10-07 12:36
 * @Description:ForkJoin应用示例
 */
public class ForkJoinPoolTest {
	private static int[] data = {1,2,3,4,5,6,7,8,9,10};

	public static void main(String[] args) {
		System.out.println("普通计算的结果："+calc());
		AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0,data.length,data);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Integer result = forkJoinPool.invoke(task);
		System.out.println("AccumulatorRecursiveTask: "+result);

		AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0,data.length,data);
		forkJoinPool.invoke(action);

		System.out.println("AccumulatorRecursiveAction: "+AccumulatorRecursiveAction.AccumulatorHelper.getResult());
	}

	private static int calc(){
		int res = 0;
		for (int i = 0; i < data.length; i++) {
			res+=data[i];
		}
		return res;
	}
}

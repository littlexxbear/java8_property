package com.linchong.java8.chapter_08;

import java.util.concurrent.RecursiveTask;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_08
 * @Author:linchong
 * @CreateTime:2019-10-07 12:39
 * @Description:
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

	private final int start;
	private final int end;
	private final int[] data;
	private final int LIMIT = 3;

	public AccumulatorRecursiveTask(int start, int end, int[] data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected Integer compute() {
		if((end-start)<=LIMIT){
			int res = 0;
			for (int i = start; i <end ; i++) {
				res += data[i];
			}
			return res;
		}
		int mid = ((end-start)>>1)+start;

		AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start,mid,data);
		AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid,end,data);
		left.fork();

		Integer rightResult = right.compute();
		Integer leftResult = left.join();
		return rightResult+leftResult;
	}


}

package com.linchong.java8.chapter_08;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_08
 * @Author:linchong
 * @CreateTime:2019-10-07 13:50
 * @Description: 无返回值
 */
public class AccumulatorRecursiveAction extends RecursiveAction {


	private final int start;
	private final int end;
	private final int[] data;
	private final int LIMIT = 3;

	public AccumulatorRecursiveAction(int start, int end, int[] data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected void compute() {
		if((end-start)<=LIMIT){
			for (int i = start; i <end ; i++) {
				AccumulatorHelper.accumulate(data[i]);
			}
		}else{
			int mid = ((end-start)>>1)+start;
			AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start,mid,data);
			AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid,end,data);

			left.fork();
			right.fork();

			left.join();
			right.join();

		}

	}

	static class AccumulatorHelper{
		private static final AtomicInteger result = new AtomicInteger(0);
		static void accumulate(int value){
			result.getAndAdd(value);
		}
		public static int getResult(){
			return result.get();
		}

		static void reset(){
			result.set(0);
		}
	}
}

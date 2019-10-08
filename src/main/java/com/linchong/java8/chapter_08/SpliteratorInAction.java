package com.linchong.java8.chapter_08;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_08
 * @Author:linchong
 * @CreateTime:2019-10-07 22:31
 * @Description:
 */
public class SpliteratorInAction {

	private static String text = "it is a simple test"+
			"\n"+
			"just a test"+
			"\n"+
			"get you way"+
			"\n"+
			"do not stop him";
	public static void main(String[] args) {
		/*IntStream intStream = IntStream.rangeClosed(0,10);
		Spliterator.OfInt spliterator = intStream.spliterator();
		Consumer<Integer> consumer = i -> System.out.println(i);
		spliterator.forEachRemaining(consumer);*/
		MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
		Optional.ofNullable(mySpliteratorText.stream().count())
		.ifPresent(System.out::println);

		mySpliteratorText.stream().forEach(System.out::println);

		//过滤空格
		//mySpliteratorText.stream().filter(s->!s.equals("")).forEach(System.out::println);

		mySpliteratorText.parallelStream().filter(s->!s.equals("")).forEach(System.out::println);
	}

	//自定义spliterator,处理字符串
	static class MySpliteratorText{
		private final String[] data;

		public MySpliteratorText(String text) {
			Objects.requireNonNull(text,"the parameter can not be null");
			this.data = text.split("\n");
		}

		public Stream<String> stream(){
			return StreamSupport.stream(new MySpliterator(),false); //false表示不是并行的
		}

		public Stream<String> parallelStream(){
			return StreamSupport.stream(new MySpliterator(),true); //false表示不是并行的
		}

		private class MySpliterator implements Spliterator<String>{

			private int start,end;

			public MySpliterator(){
				this.start = 0;
				this.end = MySpliteratorText.this.data.length - 1;
			}

			public MySpliterator(int start,int end){
				this.start = start;
				this.end = end;
			}

			//获取流中的元素，进行操作；有,直接操作;没有，返回false
			@Override
			public boolean tryAdvance(Consumer<? super String> action) {
				if(start<=end){
					action.accept(MySpliteratorText.this.data[start++]);
					return true;
				}
				return false;
			}

			@Override
			public Spliterator<String> trySplit() {
				int mid = ((end-start)>>1)/2;
				if(mid <= 1){
					return null;
				}
				int left = start;
				int right = start+mid;
				start = start+mid+1;
				return new MySpliterator(left,right);
			}

			//有没有元素
			@Override
			public long estimateSize() {
				return end - start;
			}

			@Override
			public long getExactSizeIfKnown() {
				return estimateSize();
			}

			//特征值,不可变，大小固定，可取子集
			@Override
			public int characteristics() {
				return IMMUTABLE|SIZED|SUBSIZED;
			}
		}
	}
}

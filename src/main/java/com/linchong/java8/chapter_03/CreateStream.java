package com.linchong.java8.chapter_03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-19 17:25
 * @Description:
 */
public class CreateStream {
	/**
	 * 从集合中返回Stream对象
	 *
	 * @return
	 */
	private static Stream<String> createStreamFromCollection() {
		List<String> list = Arrays.asList("hello", "Java", "Python", "PHP", "Android");
		return list.stream();
	}

	private static Stream<String> createStreamFromValues() {
		return Stream.of("hello", "Java", "Python", "PHP", "Android");
	}

	private static Stream<String> createcreateStreamFromArrays(){
		return Arrays.stream(new String[]{"hello", "Java", "Python", "PHP", "Android"});
	}

	private static Stream<String> createStreamFromFile(){
		Path path = Paths.get("H:\\Company\\java8_property\\src\\main\\java\\com\\linchong\\java8\\chapter_03\\CreateStream.java");
		try(Stream<String> lines = Files.lines(path)) {
			lines.forEach(System.out::println);
			return lines;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//通过iterator去迭代创建
	private static Stream<Integer> createScreateStreamFromIterator(){
		//无线执行，产生无线stream，使用limit限制
		Stream<Integer> stream = Stream.iterate(0,n->n+2).limit(10);
		return stream;
	}

	//通过generate
	private static Stream<Double> createStreamFromGenerate(){
		//创建无限元素，使用limit限制
		return Stream.generate(Math::random).limit(10);
	}
	public static void main(String[] args) {
		//Stream中顺序没有使用sort化，顺序和输入的一致
		/*createStreamFromCollection().forEach(System.out::println);
		createStreamFromValues().forEach(System.out::println);
		createcreateStreamFromArrays().forEach(System.out::println);
		Stream<String> streamFromFile = createStreamFromFile();
		System.out.println(streamFromFile);*/

		//createScreateStreamFromIterator().forEach(System.out::println);
		//createStreamFromGenerate().forEach(System.out::println);
		createObjStreamFromGenerate().forEach(System.out::println);
	}

	private static Stream<Obj> createObjStreamFromGenerate(){

		return Stream.generate(new ObjSupplier()).limit(10);
	}
	//自定义一个Supplier，T get();创建时需要用到
	static class ObjSupplier implements Supplier<Obj>{

		private int index = 0;
		private Random random = new Random(System.currentTimeMillis());
		@Override
		public Obj get() {
			index = random.nextInt(100);
			return new Obj(index,"Name-->"+index);
		}
	}

	//创建一个自定义Generate
	static class Obj{
		private int id;
		private String name;

		public Obj(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Obj{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}
	}
}

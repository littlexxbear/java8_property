package com.linchong.java8.practise;

import com.linchong.java8.chapter_03.Dish;

import java.util.*;

import static java.util.stream.Collectors.toList;


/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.practise
 * @Author:linchong
 * @CreateTime:2019-09-16 23:02
 * @Description:
 */
public class StreamPractise {

	public static void main(String[] args) {
		List<Dish> menuList = Arrays.asList(
				Dish.builder().name("地三鲜").calories(100).type(Dish.Type.OTHER).build(),
				Dish.builder().name("干炸里脊").calories(210).type(Dish.Type.MEAT).build(),
				Dish.builder().name("干炸黄花鱼").calories(180).type(Dish.Type.FISH).build(),
				Dish.builder().name("老醋花生").calories(240).type(Dish.Type.OTHER).build(),
				Dish.builder().name("焖饼").calories(200).type(Dish.Type.OTHER).build(),
				Dish.builder().name("东坡肉").calories(450).type(Dish.Type.MEAT).build()
		);


		List<String> result = new ArrayList<>();
		System.out.println("传统做法：");
		//选出其中卡路里低于220的菜品，并降序输出
		result = getDishListByColories(menuList);
		for (String dishName : result) {
			System.out.print(dishName+" ");
		}
		System.out.println();
		System.out.println("引入Stream流后：");
		//引入Stream后
		getDishByStream(menuList).stream().forEach(a-> System.out.print(a+" "));

	}
	//引入Stream
	private static List<String> getDishByStream(List<Dish> dishList){
		return dishList.stream().filter(a -> a.getCalories() < 220).sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName).collect(toList());
	}

	//常规做法：
	private static List<String> getDishListByColories(List<Dish> dishList){
		ArrayList<Dish> list = new ArrayList<>();
		for (Dish dish : dishList) {
			if(dish.getCalories()<=220){
				list.add(dish);
			}
		}

		Collections.sort(list, new Comparator<Dish>() {
			@Override
			public int compare(Dish o1, Dish o2) {
				return o1.getCalories()- o2.getCalories();
			}
		});

		//或者使用
		//Collections.sort(list,(a,b)->Integer.compare(a.getCalories(),b.getCalories()));

		List<String> nameList = new ArrayList<>();
		for (Dish dish : list) {
			nameList.add(dish.getName());
		}
		return nameList;
	}
}

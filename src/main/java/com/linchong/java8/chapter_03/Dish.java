package com.linchong.java8.chapter_03;

import lombok.*;

import java.lang.reflect.Type;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_03
 * @Author:linchong
 * @CreateTime:2019-07-10 13:44
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
public class Dish {

	private final String name;
	private final boolean vegetarian;
	private final int calories;

	private final Type type;

	public enum Type {MEAT,OTHER,FISH}
}

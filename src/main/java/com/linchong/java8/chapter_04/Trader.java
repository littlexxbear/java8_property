package com.linchong.java8.chapter_04;

import lombok.Data;
import lombok.ToString;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_04
 * @Author:linchong
 * @CreateTime:2019-07-21 22:22
 * @Description:
 */
@Data
@ToString
public class Trader {
	private final String name;
	private final String city;

	public Trader(String n,String c){
		this.name = n;
		this.city = c;
	}
}

package com.linchong.java8.chapter_05.v2;

import lombok.Data;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 10:16
 * @Description: 保险
 */
@Data
public class Insurance {
	private String name;

	public  String getName() {
		return name;
	}
}

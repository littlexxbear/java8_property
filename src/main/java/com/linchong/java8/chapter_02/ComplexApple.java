package com.linchong.java8.chapter_02;

import lombok.*;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.character_02
 * @Author:linchong
 * @CreateTime:2019-07-10 12:07
 * @Description:复杂的Apple对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplexApple {
	private String color;
	private String name;
	private Long weight;


}

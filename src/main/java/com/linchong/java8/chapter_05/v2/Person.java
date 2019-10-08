package com.linchong.java8.chapter_05.v2;


import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 10:14
 * @Description:
 */
@Getter
@ToString
public class Person {
	private Optional<Car> car;
	public Optional<Car> getCargetCard() {
	  return this.car;
	};
}

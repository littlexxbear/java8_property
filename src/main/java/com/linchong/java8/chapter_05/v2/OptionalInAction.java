package com.linchong.java8.chapter_05.v2;

import java.util.Optional;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 11:15
 * @Description:
 */
public class OptionalInAction {
	public static void main(String[] args) {
		  Optional.ofNullable(getInsuranceNameByOptional(null))
		  .ifPresent(System.out::println);
	}

	private static String getInsuranceNameByOptional(Person person){
		/*Optional.ofNullable(person)
				.map(Person::getCar)
				.map(Car::getInsurance)
				.map(Insurance::getName);*/
		return Optional.ofNullable(person)
				.flatMap(Person::getCar)
				.flatMap(Car::getInsurance)
				.map(Insurance::getName).orElse("Unkown");

	}
}

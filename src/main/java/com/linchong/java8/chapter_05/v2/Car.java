package com.linchong.java8.chapter_05.v2;


import lombok.Data;
import lombok.Getter;

import java.util.Optional;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 10:15
 * @Description:
 */
@Getter
@Data
public class Car {
	 private Optional<Insurance> insurance;
	 public  Optional<Insurance> getInsurance(){
	 	return insurance;
	 }

}

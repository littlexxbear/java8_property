package com.linchong.java8.chapter_02;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.character_02
 * @Author:linchong
 * @CreateTime:2019-07-10 12:12
 * @Description:自定义传入多个function
 * 传入P,U,K三个参数，返回R
 */
@FunctionalInterface
public interface ThreeFunction<T,U,K,R>{

	R apply(T t,U u,K k);

}

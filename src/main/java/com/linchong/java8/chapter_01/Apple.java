package com.linchong.java8.chapter_01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8
 * @Author:linchong
 * @CreateTime:2019-07-07 22:04
 * @Description:苹果实体类
 *  @Data: 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
*  @Setter：注解在属性上:为属性提供 setting 方法,       注解再类上表示当前类中所有属性都生成setter方法
*  @Getter：注解在属性上：为属性提供 getting 方法， 注解再类上表示当前类中所有属性都生成getter方法
*  @Log4j：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
*  @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
*  @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
   private String color;
   private long weight;
}

package com.linchong.java8.chapter_05.v1;

import java.util.Optional;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 10:14
 * @Description: Optional API详解
 */
public class OptionalUsage {
	public static void main(String[] args) {

		/**
		 * Optional构造的方法：
		 *      of
		 *      empty
		 *      ofNullable(of/empty)
		 *      get
		 *      orElse
		 *      orElseGet
		 *      orElseThow
		 */
		Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();
		/*insuranceOptional.get(); //NoSuchElemcentException

		Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());
		insuranceOptional1.get();

		Optional<Insurance> objectOptional = Optional.ofNullable(null);
		//有值，返回；没有值，返回定义的对象。 orElseGet返回supplier对象
		objectOptional.orElseGet(Insurance::new); //有值，返回；无值，返回对象。
		//返回Reference对象
		objectOptional.orElse(new Insurance());
		//有值返回值，没有值，返回异常
		objectOptional.orElseThrow(RuntimeException::new);

		objectOptional.orElseThrow(()->new RuntimeException("Not Have reference"));
*/

		/**
		 * filter,入参Predicate,
		 */

		/*Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());


		Insurance insurance = insuranceOptional1.filter(i -> i.getName() == null).get();
		System.out.println(insurance );*/

		//非空，返回；空，返回指定的值
		/*Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());
		Optional<String> nameOptional = insuranceOptional1.map(i->i.getName());
		System.out.println(nameOptional.orElse("empth value"));*/

		//isParent,返回boolean
		/*System.out.println(nameOptional.isPresent());

		nameOptional.ifPresent(System.out::println);*/

		System.out.println(getInsuranceName(null));
		System.out.println(getInsuranceNameByOptional(null));
	}
	private static String getInsuranceName(Insurance insurance){
		if(null==insurance)
			return "unknown";
		return insurance.getName();
	}
	//不想判断，使用函数式编程
	private static String getInsuranceNameByOptional(Insurance insurance){
		return  Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
	}
}

package com.linchong.java8.chapter_05.v1;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_05
 * @Author:linchong
 * @CreateTime:2019-07-22 10:17
 * @Description: 空指针异常模拟
 */
public class NullPointException {
	private static String getInstanceName(Person person){
		return person.getCar().getInsurance().getName();
	}

	private static String getInsuranceNameByDeepDoubts(Person person){
		if(null!=person){
			Car car = person.getCar();
			if(null!=car){
				Insurance insurance = car.getInsurance();
				if(null!=insurance){
					return insurance.getName();
				}
			}
		}
		return "UNKNOWN";
	}

	private static  String getInsuranceNameByMultExit(Person person){
		final String defauleValue = "UNKNOWN";
		if(null == person)
			return defauleValue;
		Car car = person.getCar();
		if(null==car)
			return defauleValue;
		Insurance insurance = car.getInsurance();
		if(null==insurance)
			return defauleValue;
		return person.getCar().getInsurance().getName();
	}
	public static void main(String[] args) {
		//String instanceName = getInstanceName(new Person());
		String result = getInsuranceNameByDeepDoubts(new Person());
		System.out.println(result);
	}
}

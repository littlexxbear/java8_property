package com.linchong.java8.chapter_01;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8
 * @Author:linchong
 * @CreateTime:2019-07-07 22:44
 * @Description:匿名内部类
 */
public class AnonymousInnerClass {
	public final int value = 4;
	public void doIt(){
		int value = 6;
		Runnable r = new Runnable() {
			public final int value = 7;
			@Override
			public void run() {
				int value = 8;
				System.out.println(this.value);
			}
		};
		r.run();
	}

	public static void main(String[] args) {
		AnonymousInnerClass anonymousInnerClass = new AnonymousInnerClass();
		anonymousInnerClass.doIt();
	}
}

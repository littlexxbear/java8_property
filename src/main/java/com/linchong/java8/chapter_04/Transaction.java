package com.linchong.java8.chapter_04;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_04
 * @Author:linchong
 * @CreateTime:2019-07-21 22:26
 * @Description:
 */
@Data
@ToString
public class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader(){
		return this.trader;
	}

}

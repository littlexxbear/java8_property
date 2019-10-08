package com.linchong.java8.chapter_04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @BelongsProject:java8_property
 * @BelongsPackage:com.linchong.java8.chapter_04
 * @Author:linchong
 * @CreateTime:2019-07-21 22:26
 * @Description:
 */
public class StreamInAction {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul","Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian= new Trader("Brian","Cambridge");


		List<Transaction> transactions = Arrays.asList(
	 		new Transaction(brian,2011,300),
	 		new Transaction(raoul,2012,1000),
	 		new Transaction(raoul,2011,400),
	 		new Transaction(mario,2012,710),
	 		new Transaction(mario,2012,700),
	 		new Transaction(alan,2012,950)
		);

		//1.获取所有在2011年的交易，并且按照交易的量由小到大对它们进行排序
		/*List<Transaction> result = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.collect(toList());
		System.out.println(result);*/

		//2.在贸易产品中，那些是唯一的城市？
		/*transactions.stream().map(t->t.getTrader().getCity())
				.distinct().forEach(System.out::println);*/

		//3.查找出所有来自于Cambridge的交易员，并按照名字对他们进行排序
		/*transactions.stream().map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.forEach(System.out::println);*/
		//4.按照ascII码排序，返回所有交易员的名字
		String value = transactions.stream().map(transaction -> transaction.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("", (name1, name2) -> name1 + name2);

		System.out.println(value);

		//5.是否存在来自Milan的交易员
		boolean liveInMilan1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		boolean liveInMilan2 = transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equals("Milan"));
		System.out.println(liveInMilan1);
		System.out.println(liveInMilan2);



		//6.打印出所有来自于Cambridge交易员的商品的值
		transactions.stream()
				.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
		.forEach(System.out::println);

		//7.求所有商品中最高价值的产品
		Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);//(i,j)->i>j?i:j
		System.out.println(maxValue.get());

		//8.找出所有商品中价格最低的商品
		Optional<Integer> minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
		System.out.println(minValue.get());
	}

}

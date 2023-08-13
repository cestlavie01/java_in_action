package com.cobus.javainaction.chapter_1;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chapter1Main {
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple(Apple.Color.GREEN, 70));
		inventory.add(new Apple(Apple.Color.GREEN, 170));
		inventory.add(new Apple(Apple.Color.RED, 60));
		inventory.add(new Apple(Apple.Color.RED, 160));
		inventory.add(new Apple(Apple.Color.BLUE, 260));
		
		System.out.println(Apple.filterApples(inventory, Apple::isGreenApple));
		System.out.println(Apple.filterApples(inventory, Apple::isHeavyApple));
		System.out.println(Apple.filterApples(inventory, (Apple a) -> Apple.Color.BLUE.equals(a.getColor())));
		
		List<Transaction> transactions= new ArrayList<>();
		
Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
for(Transaction transaction : transactions) {
	if (transaction.getPrice() > 1000) {
		Currency currency = transaction.getCurrency();
		List<Transaction> transactionForCurrency = transactionsByCurrencies.get(currency);
		if (transactionForCurrency == null) {
			transactionForCurrency = new ArrayList<>();
			transactionsByCurrencies.put(currency, transactionForCurrency);
		}

		transactionForCurrency.add(transaction);
	}
}

Map<Currency, List<Transaction>> transactionsByCurrencies2 = 
		transactions.stream()
			.filter((Transaction t) -> t.getPrice() > 1000)
			.collect(groupingBy(Transaction::getCurrency));
	}	
}

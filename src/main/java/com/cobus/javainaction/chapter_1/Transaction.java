package com.cobus.javainaction.chapter_1;

public class Transaction {
	private int price = 0;
	private Currency currency = null;
	
	public Transaction(final int price, Currency currency) {
		this.price = price;
		this.currency = currency;
	}
	
	public int getPrice() {
		return price;
	}
	
	public Currency getCurrency() {
		return currency;
	}
}
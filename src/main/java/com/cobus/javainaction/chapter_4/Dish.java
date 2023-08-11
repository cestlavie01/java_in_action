package com.cobus.javainaction.chapter_4;

import lombok.Getter;

@Getter
public class Dish {
	String name;
	boolean vegetarian;
	int calories;
	Type type;

	enum Type {
		MEAT, FISH, OTHER
	};

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		calories = 0;
	}
}

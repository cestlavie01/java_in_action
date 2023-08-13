package com.cobus.javainaction.chapter_4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Dish {
	String name;
	boolean vegetarian;
	int calories;
	Type type;

	enum Type {
		MEAT, FISH, OTHER
	};

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public int getCalories() {
		return calories;
	}
	
	public String getName() {
		return name;
	}
}

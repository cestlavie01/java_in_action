package com.cobus.javainaction.chapter_4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Dish {
	String name;
	boolean vegetarian;
	int calories;
	Type type;

	public enum Type {
		MEAT, FISH, OTHER
	};
}

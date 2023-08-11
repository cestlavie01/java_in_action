package com.cobus.javainaction.chapter_3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Fruit {
	static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
	static {
		map.put("apple", Apple::new);
		map.put("orange", Orange::new);
		// add more
	}
	
	public static Fruit giveMeFruit(String fruit, Integer weight) {
		return map.get(fruit.toLowerCase()).apply(weight);
	}
	
	private Integer weight = 0;

	public Fruit(Integer weight) {
		this.weight = weight;
	}
}

package com.cobus.javainaction.chapter_1;

import java.util.ArrayList;
import java.util.List;

public class Apple {
	public enum Color {
		GREEN,
		RED,
		BLUE,
	}
	
	private Color color = Color.GREEN;
	private int weight = 0;
	
	public Apple(Color color, int weight) {
		this.color = color;
		this.weight = weight;
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;
	}

	public Color getColor() {
		return color;
	}

	public int getWeight() {
		return weight;
	}

	public static boolean isGreenApple(Apple apple) {
		return Color.GREEN.equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	public interface Predicate<T> {
		boolean test(T t);
	}
	
	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %d", color, weight);
	}
}

package com.cobus.javainaction.chapter_2;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Apple2 {
	public enum Color {
		GREEN,
		RED,
		BLUE,
	}
	
	private Color color = Color.GREEN;
	private Integer weight = 0;
	
	public Apple2(Color color, int weight) {
		this.color = color;
		this.weight = weight;
	}
	
	public static List<Apple2> filterGreenApples(List<Apple2> inventory, final Color color) {
		List<Apple2> result = new ArrayList<>();
		for (Apple2 apple : inventory) {
			if (color.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple2> filterHeavyApples(List<Apple2> inventory, final int weight) {
		List<Apple2> result = new ArrayList<>();
		for (Apple2 apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple2> filterApples1(List<Apple2> inventory,
			final Color color, final int weight, boolean flag) {
		List<Apple2> result = new ArrayList<>();
		for (Apple2 apple : inventory) {
			if (flag && apple.getColor().equals(color)
					|| !flag && apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple2> filterApples2(List<Apple2> inventory,
			final Color color, final int weight) {
		List<Apple2> result = new ArrayList<>();
		for (Apple2 apple : inventory) {
			if (color != null && apple.getColor().equals(color)
					|| weight >= 0 && apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple2> filterApples3(List<Apple2> inventory, ApplePredicate p) {
		List<Apple2> result = new ArrayList<>();
		for (Apple2 apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Apple2 [color=" + color + ", weight=" + weight + "]";
	}
}


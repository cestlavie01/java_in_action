package com.cobus.javainaction.chapter_4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chapter4Main {
	public static void main(String[] args) {
		Chapter4Main main = new Chapter4Main();
		main.runCodeBeforeJava18();
		main.runCodeAfterJava18();
	}

	public void runCodeBeforeJava18() {
		List<Dish> menu = new ArrayList<>();

		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish dish : menu) {
			if (dish.getCalories() < 400) {
				lowCaloricDishes.add(dish);
			}
		}

		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});

		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish dish : lowCaloricDishes) {
			lowCaloricDishesName.add(dish.getName());
		}
	}
	
	public void runCodeAfterJava18() {
		List<Dish> menu = new ArrayList<>();
		List<String> lowCaloricDishesName = menu.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories)).map(Dish::getName)
				.collect(toList());
	}
}

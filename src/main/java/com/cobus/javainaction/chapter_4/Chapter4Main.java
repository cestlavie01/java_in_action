package com.cobus.javainaction.chapter_4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Chapter4Main {
	List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH)
	);
	
	public static void main(String[] args) {
		Chapter4Main main = new Chapter4Main();
		main.runBeforeJava8Style();
		main.runAfterJava8Style();
		main.streamCanCallOnlyOneTime();
		main.externalIterationVsInternalIteration();
		main.testRefactoring();
		main.likeDebugging();
	}
	
	public void runBeforeJava8Style() {
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

	public void runAfterJava8Style() {
		List<String> lowCaloricDishesName = 
				menu.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(toList());
		System.out.println(lowCaloricDishesName);
		
		List<String> threeHighCaloriesDishNames = 
				menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(toList());
		System.out.println(threeHighCaloriesDishNames);
	}
	
	public void streamCanCallOnlyOneTime() {
		List<String> title = Arrays.asList("Java", "In", "Action");
		Stream<String> s = title.stream();
		s.forEach(System.out::println);
		// Will occured exception.
		// java.lang.IllegalStateException: stream has already been operated upon or closed
//		s.forEach(System.out::printf);
	}
	
	public void externalIterationVsInternalIteration() {
		// external iteration
		List<String> names1 = new ArrayList<>();
		for(Dish dish: menu) {
			names1.add(dish.getName());
		}
		
		// external iteration
		List<String> names2 = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while(iterator.hasNext()) {
			Dish dish = iterator.next();
			names2.add(dish.getName());
		}
		
		// internal iteration
		List<String> names3 = menu.stream()
				.map(Dish::getName)
				.collect(toList());
	}
	
	public void testRefactoring() {
		List<String> highCaloriesDisheNames1 = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while(iterator.hasNext()) {
			Dish dish = iterator.next();
			if (dish.getCalories() > 300) {
				highCaloriesDisheNames1.add(dish.getName());
			}
		}
		System.out.println(highCaloriesDisheNames1);
		
		List<String> highCaloriesDisheNames2 = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.collect(toList());
		System.out.println(highCaloriesDisheNames2);
	}
	
	public void likeDebugging() {
List<String> threeHighCaloriesDishNames = 
		menu.stream()
		.filter(d -> {
			System.out.println("filtering:" + d.getName());
			return d.getCalories() > 300;
		})
		.map(d -> {
			System.out.println("mapping:" + d.getName());
			return d.getName();
		})
		.limit(3)
		.collect(toList());
System.out.println(threeHighCaloriesDishNames);
	}
}

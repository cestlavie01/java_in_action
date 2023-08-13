package com.cobus.javainaction.chapter_5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import com.cobus.javainaction.chapter_4.Dish;

public class Chapter5Main {
	List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH));

	public static void main(String[] args) {
		Chapter5Main main = new Chapter5Main();
		main.filterVegetarian();
		main.testDistinct();
		main.testSlicing();
		main.testMapping();
	}

	public void filterVegetarian() {
		List<Dish> vegeterianMenu = menu.stream()
				.filter(Dish::isVegetarian)
				.collect(toList());

		System.out.println(vegeterianMenu);
	}

	public void testDistinct() {
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.forEach(System.out::println);
	}

	public void testSlicing() {
		List<Dish> menu = Arrays.asList(
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER));

		List<Dish> filteredMenu = menu.stream()
				.filter(d -> d.getCalories() < 320)
				.collect(toList());
		System.out.println(filteredMenu);

		List<Dish> slicedMenu1 = menu.stream()
				.takeWhile(dish -> dish.getCalories() < 320)
				.collect(toList());
		System.out.println(slicedMenu1);

		List<Dish> slicedMenu2 = menu.stream()
				.dropWhile(dish -> dish.getCalories() < 320)
				.collect(toList());
		System.out.println(slicedMenu2);

		List<Dish> slicedMenu3 = menu.stream()
				.filter(dish -> dish.getCalories() > 300)
				.limit(3)
				.collect(toList());
		System.out.println(slicedMenu3);

		List<Dish> slicedMenu4 = menu.stream()
				.filter(dish -> dish.getCalories() >= 300)
				.skip(2)
				.collect(toList());

		// [Dish(name=chicken, vegetarian=false, calories=400, type=MEAT)
		// Dish(name=french fries, vegetarian=true, calories=530, type=OTHER)]
		System.out.println(slicedMenu4);

		List<Dish> slicedMenu5 = menu.stream()
				.filter(dish -> dish.getCalories() >= 300)
				.skip(2)
				.limit(1)
				.collect(toList());

		// [Dish(name=chicken, vegetarian=false, calories=400, type=MEAT)]
		System.out.println(slicedMenu5);

		List<Dish> slicedMenu6 = menu.stream()
				.filter(dish -> dish.getType() == Dish.Type.MEAT)
				.limit(2)
				.collect(toList());
		System.out.println(slicedMenu6);
	}

	public void testMapping() {
		List<String> dishNames = menu.stream()
				.map(Dish::getName)
				.collect(toList());
		System.out.println(dishNames);

		List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
		List<Integer> wordLengths = words.stream()
				.map(String::length)
				.collect(toList());
		System.out.println(wordLengths);

		List<Integer> dishNameLength = menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(toList());
		System.out.println(dishNameLength);

		List<String> words2 = Arrays.asList("Hello", "World");
		words2.stream()
				.map(w -> w.split(""))
				.distinct()
				.collect(toList());

		String[] arrayOfWords = { "Goodbye", "World" };
		Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
		List<Stream<String>> streamOfWords2 = words2.stream()
				.map(w -> w.split(""))
				.map(Arrays::stream)
				.distinct()
				.collect(toList());
		System.out.println(streamOfWords2);

		List<String> uniqueCharacters = words2.stream()
				.map(w -> w.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());
		System.out.println(uniqueCharacters);

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> pows = numbers.stream()
				.map(n -> n * n)
				.collect(toList());
		System.out.println(pows);

		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		// TODO
	}
}

package com.cobus.javainaction.chapter_5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		main.testSearchingAndMatching();
		main.testReducing();
		main.test56();
		main.testNumericStream();
		main.testOtherStreams();
		main.testIterate();
		main.testGenerate();
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
		List<Integer[]> result = numbers1.stream()
				.flatMap(n1 -> numbers2.stream()
						.map(n2 -> new Integer[]{n1, n2})
				)
				.collect(toList());
		for (Integer[] pair : result) {
			System.out.println(pair[0] + "," + pair[1]);
		}

		List<Integer[]> result2 = numbers1.stream()
				.flatMap(n1 -> numbers2.stream()
						.filter(n2 -> (n1 + n2) % 3 == 0)
						.map(n2 -> new Integer[]{n1, n2})
				)
				.collect(toList());
		for (Integer[] pair : result2) {
			System.out.println(pair[0] + "," + pair[1]);
		}
	}

	public void testSearchingAndMatching() {
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("This menu is (somethat) vegetarian friendly!!");
		}

		if(menu.stream().allMatch(d -> d.getCalories() < 1000)) {
			System.out.println("This restaurant is so healthy!!");
		}

		if(menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
			System.out.println("This restaurant is so heavy!!");
		}

		Optional<Dish> dish = menu.stream()
				.filter(Dish::isVegetarian)
				.findAny();
		System.out.println(dish.toString());

		menu.stream()
				.filter(Dish::isVegetarian)
				.findAny()
				.ifPresent(d -> System.out.println(d.getName()));

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
				.map(n -> n * n)
				.filter(n -> n % 3 == 0)
				.findFirst();
		System.out.println(firstSquareDivisibleByThree);
	}

	public void testReducing() {
		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		int sum = 0;
		for (Integer n : someNumbers) {
			sum += n;
		}
		System.out.println(sum);

		int sum2 = someNumbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println(sum2);

		Optional<Integer> sum3 = someNumbers.stream().reduce((a, b) -> a + b);
		System.out.println(sum3);

		List<Integer> emptyList = Arrays.asList();
		Optional<Integer> sum4 = emptyList.stream().reduce((a, b) -> a + b);
		System.out.println(sum4);

		Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
		System.out.println(max);

		Optional<Integer> min = someNumbers.stream().reduce(Integer::min);
		System.out.println(min);

		int sum5 = someNumbers.stream().map(n -> 1).reduce(0, (a, b) -> a + b);
		System.out.println(sum5);
	}

	public void test56() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
	
		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		// q1
		transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.forEach(t -> System.out.println(t.toString()));

		// q2
		transactions.stream()
				.map(t -> t.getTrader().getCity())
				.distinct()
				.forEach(city -> System.out.println(city));

		// q3
		transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.map(t -> t.getTrader())
				.distinct()
				.sorted(comparing(Trader::getName))
				.forEach(t -> System.out.println(t.toString()));

		// q4
		String result = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("", (a, b) -> a + b);
		System.out.println(result);
				
		// q5
		if (transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"))) {
			System.out.println("Somebody in Milan");
		}

		// q6
		transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.distinct()
				.map(Transaction::getValue)
				.forEach(t -> System.out.println(t));

		// q7
		Integer n1 = transactions.stream()
				.map(t -> t.getValue())
				.reduce(0, Integer::max);
		System.out.println(n1);

		// q8
		Optional<Integer> n2 = transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::min);
		System.out.println(n2.get());
	}

	public void testNumericStream() {
		int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println("" + totalCalories + "," + totalCalories2);

		IntStream IntStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = IntStream.boxed();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
		System.out.println(maxCalories); // OptionalInt[800]

		List<Dish> emptyList = new ArrayList<>();
		OptionalInt emtpyElimentMax = emptyList.stream().mapToInt(Dish::getCalories).max();
		System.out.println(emtpyElimentMax); // OptionalInt.empty
		int max = emtpyElimentMax.orElse(1);
		System.out.println(max); // 1

		IntStream evenNumbers = java.util.stream.IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbers.count());

		Stream<int[]> pythagoreanTriples = java.util.stream.IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a ->
					java.util.stream.IntStream.rangeClosed(1, 100)
					.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
					.mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)})
				);

		pythagoreanTriples.limit(5)
				.forEach(t ->
					System.out.println(String.format("%d, %d, %d,", t[0], t[1], t[2])));

		Stream<int[]> pythagoreanTriples2 = java.util.stream.IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a ->
					java.util.stream.IntStream.rangeClosed(1, 100)
					.mapToObj(b ->
						new int[]{a, b, (int) Math.sqrt(a*a + b*b)})
					.filter(t -> t[2] % 1 == 0)
				);
	}

	public void testOtherStreams() {
		Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);
		
		Stream<String> emptyStringStream = Stream.empty();
		Stream<Integer> emptyIntStream = Stream.empty();

		Stream<String> stream2 = Stream.of("Modern", "Java", "In", "Action");

		// prepending
		Stream<String> sstream1 = Stream.concat(Stream.of("cobus"), stream2);
		// sstream1.map(String::toUpperCase).forEach(System.out::println); // "COBUS"

		// Appending
		Stream<String> sstream2 = Stream.concat(sstream1, Stream.of("blog"));
		sstream2.map(String::toUpperCase).forEach(System.out::println); // "COBUS" "BLOG"

		String homeValue = System.getProperty("home");
		Stream<String> homevaluStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
		Stream<String> homevaluStream2 = Stream.ofNullable(System.getProperty("home"));

		System.out.println(homevaluStream.count()); // 0
		System.out.println(homevaluStream2.count()); // 0

		int[] numbers = { 2, 3, 5, 7, 9, 11, 13 };
		int sum = Arrays.stream(numbers).sum();
		System.out.println(sum);

		long uniqueWords = 0;
		try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
		} catch (IOException e) {
			// Do something for exception.
		}
	}

	public void testIterate() {
		Stream.iterate(0, n -> n + 2)
				.limit(10)
				.forEach(System.out::println);

		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
				.limit(20)
				.forEach(n -> System.out.println("(" + n[0] + "," + n[1] + ")"));

		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
				.limit(20)
				.map(n -> n[0])
				.forEach(System.out::println);

		Stream.iterate(new int[] { 0, 1 }, n -> n[0] < 100, n -> new int[] { n[1], n[0] + n[1] })
				.limit(20)
				.map(n -> n[0])
				.forEach(System.out::println);

		Stream.iterate(new int[] { 0, 1 }, n -> new int[] { n[1], n[0] + n[1] })
				.takeWhile(n -> n[0] < 100)
				.limit(20)
				.map(n -> n[0])
				.forEach(System.out::println);
	}
	
	public void testGenerate() {
		Stream.generate(Math::random)
				.limit(5)
				.forEach(System.out::println);
		
		IntStream twos = IntStream.generate(new IntSupplier() {
			public int getAsInt() {
				return 2;
			}
		});

		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;
			public int getAsInt() {
				int oldPrevious = previous;
				int nextValue = previous + current;
				previous = current;
				current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}
}

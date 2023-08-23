package com.cobus.javainaction.chapter_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Chapter3Main {
	public static void main(String[] args) {
		Chapter3Main main = new Chapter3Main();
		main.test();
	}
	
	public void test() {
		List<String> listOfStrings = new ArrayList<>();
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		
		forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
		
		List<Integer> list = map(Arrays.asList("lambdas", "in", "action"),
				(String s) -> s.length());
		System.out.println(list); // [7, 2, 6]
		
		IntPredicate audultPredicate = (int age) -> age > 19;

		Consumer<String> c = s -> returnBooleanTesting();
		
		int portNumber = 1337;
		Runnable r = () -> {
			System.out.println(portNumber);
//			portNumber = 13337;	// compile error
		};
//		portNumber = 13337; // compile error
		r.run();
		
		
		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		List<Apple> apples = map2(weights, Apple::new);
		System.out.println(apples);
	}
	
	public List<Apple> map2(List<Integer> list, Function<Integer, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for(Integer i: list) {
			result.add(f.apply(i));
		}
		return result;
	}
	
	public boolean returnBooleanTesting() {
		return true;
	}
	
	public <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for (T t : list) {
			if(p.test(t)) {
				results.add(t);
			}
		}
		
		return results;
	}
	
	public <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}
	
	public <T,R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}
}

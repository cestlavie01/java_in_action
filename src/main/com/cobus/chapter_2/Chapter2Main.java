package com.cobus.chapter_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Chapter2Main {
	public static void main(String[] args) {
		List<Apple2> inventory = new ArrayList<>();
		inventory.add(new Apple2(Apple2.Color.GREEN, 70));
		inventory.add(new Apple2(Apple2.Color.GREEN, 170));
		inventory.add(new Apple2(Apple2.Color.RED, 60));
		inventory.add(new Apple2(Apple2.Color.RED, 160));
		inventory.add(new Apple2(Apple2.Color.BLUE, 260));
		
		Apple2.filterApples1(inventory, Apple2.Color.GREEN, 0, true);
		Apple2.filterApples1(inventory, null, 150, false);
		
		Apple2.filterApples2(inventory, Apple2.Color.GREEN, -1);
		Apple2.filterApples2(inventory, null, 150);
		Apple2.filterApples3(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple2 apple) {
				return apple.getColor().equals(Apple2.Color.RED);
			}
		});
		
		Apple2.filterApples3(inventory, (Apple2 apple) -> Apple2.Color.RED.equals(apple.getColor()));
		
		inventory.sort(new Comparator<Apple2>() {
			public int compare(Apple2 a1, Apple2 a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		
		inventory.sort((Apple2 a1, Apple2 a2) -> a1.getWeight().compareTo(a2.getWeight()));
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Hello World!");
			}
		});
		
		Thread t2 = new Thread(() -> System.out.println("Hello World!!"));
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> threadName1 = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Thread.currentThread().getName();
			}
		});
		System.out.println(threadName1);
		
		Future<String> threadName2 = executorService.submit(() -> Thread.currentThread().getName());
		System.out.println(threadName2);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Runnable r = new Thread();
	}
}

package com.cobus.javainaction.chapter_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cobus.javainaction.chapter_1.Currency;
import com.cobus.javainaction.chapter_1.Transaction;
import com.cobus.javainaction.chapter_4.Dish;

public class Chapter6Main {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        Chapter6Main main = new Chapter6Main();
        main.section6_1();
        main.section6_2();
        main.section6_3();
    }

    public void section6_1() {
        List<Transaction> transactions = new ArrayList<>();

        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionForCurrency = transactionsByCurrencies.get(currency);
            if (transactionForCurrency == null) {
                transactionForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionForCurrency);
            }

            transactionForCurrency.add(transaction);
        }

        Map<Currency, List<Transaction>> transactionsByCurrencies2 = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
    }

    public void section6_2() {
        long howManyDishes = menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishes);

        long howManyDishes2 = menu.stream().count();
        System.out.println(howManyDishes2);

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);

        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);

        // 안된다.
        // String shortMenu2 = menu.stream().collect(Collectors.joining());
        // System.out.println(shortMenu);

        String shortMenu3 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu3);

        int totalCalories2 = menu.stream().collect(
                Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories2);

        Optional<Dish> mostCalorieDish2 = menu.stream().collect(Collectors.reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish2);

        int totalCalories3 = menu.stream().collect(
                Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories3);

        // Quiz 6-1
        String shortMenu4 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu4);

        String shortMenu5 = menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
        System.out.println(shortMenu5);

        // Compile 오류
        // String shortMenu6 = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getName() + d2.getName())).get();

        String shortMenu7 = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        System.out.println(shortMenu7);
    }

    public void section6_3() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                Collectors.groupingBy(dish -> {
                        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT;
            }));
        System.out.println(dishesByCaloricLevel);

        // 6.3.1
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(caloricDishesByType);

        Map<Dish.Type, List<Dish>> caloricDishesByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
        System.out.println(caloricDishesByType2);

        Map<Dish.Type, List<String>> caloricDishesByType3 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println(caloricDishesByType3);
    }
}

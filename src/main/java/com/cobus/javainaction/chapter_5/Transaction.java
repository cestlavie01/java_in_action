package com.cobus.javainaction.chapter_5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}

package com.cobus.javainaction.chapter_5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Trader {
    private final String name;
    private final String city;
}

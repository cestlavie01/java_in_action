package com.cobus.chapter_2;

public class AppleGreenColorPredicate implements ApplePredicate {
	@Override
	public boolean test(Apple2 apple) {
		return apple.getColor().equals(Apple2.Color.GREEN);
	}
}

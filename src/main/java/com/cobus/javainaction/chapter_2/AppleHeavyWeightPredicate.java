package com.cobus.javainaction.chapter_2;

public class AppleHeavyWeightPredicate implements ApplePredicate {
	@Override
	public boolean test(Apple2 apple) {
		return apple.getWeight() > 150;
	}
}

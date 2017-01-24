package com.gary.common.utils;

public class Counter {
	private int count;
	public Counter(int count) {
		this.count = count;
	}
	public Counter() {}
	public void inc() {
		count++;
	}
	public int get() {
		return count;
	}
	public int getAndInc() {
		return count++;
	}
}

package com.common.guava.test;

import com.google.common.collect.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by gary on 17/1/17.
 */
public class CollectionHelpers {

	public static void main(String[] args) {

	}

	public static void peekingIterators() {
		List<String> source = Lists.newArrayList("a", "b", "ab", "c");
		List<String> result = Lists.newArrayList();
		PeekingIterator<String> iter = Iterators.peekingIterator(source.iterator());
		while (iter.hasNext()) {
			String current = iter.next();
			while (iter.hasNext() && iter.peek().equals(current)) {
				// skip this duplicate element
				iter.next();
			}
			result.add(current);
		}
	}

	public static Iterator<String> skipNulls(final Iterator<String> in) {
		return new AbstractIterator<String>() {
			protected String computeNext() {
				while (in.hasNext()) {
					String s = in.next();
					if (s != null) {
						return s;
					}
				}
				return endOfData();
			}

			// Some iterators are more easily expressed in other ways.
			// AbstractSequentialIterator provides another way of expressing an iteration.
			// just AbstractSequentialIterator sample
			private void method() {
				Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) { // note the initial value!
					protected Integer computeNext(Integer previous) {
						return (previous == 1 << 30) ? null : previous * 2;
					}
				};
			}
		};
	}
}

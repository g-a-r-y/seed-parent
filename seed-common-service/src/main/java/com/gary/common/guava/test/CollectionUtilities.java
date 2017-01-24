package com.gary.common.guava.test;

import static com.gary.common.utils.JsonMapper.defaultPrintPretty;
import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gary on 16/12/29.
 */
public class CollectionUtilities {

	public static void main(String[] args) {
		lists();
		sets();
		maps();
	}

	public static void lists() {
		List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
		List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}

		List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}

	}

	public static void sets() {
		Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
		Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

		Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength); // contains "two", "three", "seven"
		System.out.println(intersection);
		// I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
		System.out.println(intersection.immutableCopy());

		Set<String> animals = ImmutableSet.of("gerbil", "hamster");
		Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

		//Returns every possible list that can be obtained by choosing one element from each set.
		Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
		// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
		//  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}

		//Returns the set of subsets of the specified set.
		Set<Set<String>> animalSets = Sets.powerSet(animals);
		// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
	}

	public static void maps() {
		//如果用来做索引的不是唯一的
		//java.lang.IllegalArgumentException:
		//  Multiple entries with same key: 1=a and 1=a. To index multiple values under a key, use Multimaps.index.
//		List<String> strings = Lists.newArrayList("a", "a", "abc");
		List<String> strings = Lists.newArrayList("a", "ab", "abc");
		ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings, new Function<String, Integer>() {
			public Integer apply(String string) {
				return string.length();
			}
		});
//		Maps.uniqueIndex(strings, string -> string.length());
		defaultPrintPretty(stringsByIndex);

		//Maps.difference(..) example
		Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
		MapDifference<String, Integer> diff = Maps.difference(left, right);

		diff.entriesInCommon(); // {"b" => 2}  返回键值相同的
		diff.entriesDiffering(); // {"c" => (3, 4)}  返回键相同但值不同,The entries with the same keys, but differing values. The values in this map are of type MapDifference.ValueDifference, which lets you look at the left and right values.
		diff.entriesOnlyOnLeft(); // {"a" => 1}  返回在左边map而不在右边map的entries
		diff.entriesOnlyOnRight(); // {"d" => 5}  同上
	}
}

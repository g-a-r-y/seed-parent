package com.common.guava.test;

import static com.common.utils.PrintUtils.printPretty;
import static com.common.utils.JsonMapper.defaultMapper;
import com.google.common.collect.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class NewCollectionTypes {

	public static void main(String[] args) {
//		multiset();
//		multimap();
		biMap();
		table();
	}

	public static void multiset() {
		//ConcurrentHashMultiset not support null
		Multiset<String> multiset = ConcurrentHashMultiset.create();
		multiset.add("a");
		multiset.add("b", 4);
		printPretty(multiset);
		multiset.count("a");

		Multiset<String> multiset1 = HashMultiset.create();
		multiset1.add("a", 2);

		Multiset<String> multiset2 = HashMultiset.create();
		multiset2.add("a", 5);

		multiset1.containsAll(multiset2); // returns true: all unique elements are contained,
		// even though multiset1.count("a") == 2 < multiset2.count("a") == 5
		Multisets.containsOccurrences(multiset1, multiset2); // returns false

//		multiset2.removeOccurrences(multiset1); // multiset2 now contains 3 occurrences of "a"

		multiset2.removeAll(multiset1); // removes all occurrences of "a" from multiset2, even though multiset1.count("a") == 2
		multiset2.isEmpty(); // returns true
	}

	public static void multimap() {
		//Multimap.get(key)不会返回null
		Multimap<Integer, String> arrayListMultimap= ArrayListMultimap.create();
		arrayListMultimap.put(1, "a");
		arrayListMultimap.put(1, "b");
		arrayListMultimap.put(1, "b");
		arrayListMultimap.put(2, "c");
		arrayListMultimap.put(3, "d");

		printPretty(arrayListMultimap);
		printPretty(arrayListMultimap.get(1));
		printPretty(arrayListMultimap.get(2));
		printPretty(arrayListMultimap.get(4));  //空集合
		printPretty(arrayListMultimap.get(4).add("f"));  //返回的空集合是与这个key关联的

		printPretty(arrayListMultimap.asMap().get(4));  //["f"]
		printPretty(arrayListMultimap.values());  //["a","b","b","c","d","f"]

		Multimap<Integer, String> linkedHashMultimap = LinkedHashMultimap.create();
		linkedHashMultimap.put(1, "a");
		linkedHashMultimap.put(2, "b");
		linkedHashMultimap.put(1, "c");
		printPretty(linkedHashMultimap);
		printPretty(linkedHashMultimap.keySet());
		printPretty(linkedHashMultimap.values());
		Collection<Map.Entry<Integer, String>> entries = linkedHashMultimap.entries();
		entries.stream().forEach(entry -> {
			printPretty(entry.getKey());
			printPretty(entry.getValue());
		});
	}

	public static void biMap() {
		//BiMap的值不允许重复,可以反转
		BiMap<Integer, String> biMap = HashBiMap.create();
		biMap.put(1, "a");
		biMap.put(2, "b");
		biMap.put(1, "c");
//		biMap.put(3, "b");  //java.lang.IllegalArgumentException: value already present: b
		//可以用forcePut(..,..)
		biMap.forcePut(3, "b");  //会把2的删掉
		printPretty(biMap);
		printPretty(biMap.inverse());
	}

	public static void table() {
		Date now = new Date();
		Date yesterday = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
		DateOfBirth nowBirth = new DateOfBirth(now);
		DateOfBirth yesBirth = new DateOfBirth(yesterday);

		Table<DateOfBirth, String, PersonalRecord> records = HashBasedTable.create();
		records.put(nowBirth, "Schmo", new PersonalRecord(nowBirth, "Schmo"));
		records.put(nowBirth, "Doe", new PersonalRecord(nowBirth, "Doe"));
		records.put(yesBirth, "Doe", new PersonalRecord(yesBirth, "Doe"));

		defaultMapper().printPrettyJson(records.row(nowBirth)); // returns a Map mapping "Schmo" to recordA, "Doe" to recordB
		defaultMapper().printPrettyJson(records.column("Doe")); // returns a Map mapping someBirthday to recordB, otherBirthday to recordC
	}

	static class DateOfBirth {
		private Date birth;

		public DateOfBirth(Date birth) {
			this.birth = birth;
		}

		public Date getBirth() {
			return birth;
		}

		public void setBirth(Date birth) {
			this.birth = birth;
		}
	}
	static class PersonalRecord {
		private DateOfBirth birth;
		private String lasName;
		public PersonalRecord(DateOfBirth birth, String lasName) {
			this.birth = birth;
			this.lasName = lasName;
		}

		public DateOfBirth getBirth() {
			return birth;
		}

		public void setBirth(DateOfBirth birth) {
			this.birth = birth;
		}

		public String getLasName() {
			return lasName;
		}

		public void setLasName(String lasName) {
			this.lasName = lasName;
		}
	}



}
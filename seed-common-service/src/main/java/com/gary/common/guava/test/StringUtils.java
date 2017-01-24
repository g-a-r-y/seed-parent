package com.gary.common.guava.test;

import com.google.common.base.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.gary.common.utils.PrintUtils.printPretty;
import static com.gary.common.utils.PrintUtils.print;
/**
 * Created by gary on 17/1/22.
 */
public class StringUtils {

	public static void main(String[] args) {
		joiner();
		splitter();
	}

	public static void joiner() {
		Joiner joiner = Joiner.on("; ").skipNulls();
		String join = joiner.join("Harry", null, "Ron", "Hermione");
		print(join);
	}

	public static void splitter() {
		//["123","456","7890"]
		List<String> strings = Splitter.fixedLength(3).limit(3).splitToList("1234567890");
		print(strings);

		//["a","b c","d","f"]
		List<String> strings1 = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("a  ,  b c , d,f,");
		print(strings1);

		Splitter.on(CharMatcher.BREAKING_WHITESPACE);
		Splitter.on(CharMatcher.anyOf(";,."));
	}

	public static void charMatcher() {
		String string = "25ioegojOJEOJG4io3  ";
		String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); // remove control characters
		String theDigits = CharMatcher.DIGIT.retainFrom(string); // only the digits
		// trim whitespace at ends, and replace/collapse whitespace into single spaces
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
		String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); // star out all digits
		// eliminate all characters that aren't digits or lowercase
		String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);

//		StandardCharsets.UTF_8

		//CaseFormat方便于转换字符的格式,驼峰形,蛇形
		CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"); // returns "constantName"
	}
}

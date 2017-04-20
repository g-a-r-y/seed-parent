package com.common.utils;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;

public class ExpressionUtils {
	private static final ExpressionParser parser = new SpelExpressionParser();

	public static <T> T eval(String expression, Map<?, ?> context, Class<T> clazz) {
		Expression expr = parser.parseExpression(expression);
		return expr.getValue(context, clazz);
	}
}

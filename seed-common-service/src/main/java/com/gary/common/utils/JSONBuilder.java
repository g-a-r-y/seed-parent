package com.gary.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Stack;

public class JSONBuilder {
	private Stack<JSON> stack = new Stack<>();
	private String currentKey;

	private void addValue(Object value) {
		JSON current = stack.peek();
		if (current instanceof JSONObject) {
			if (currentKey != null) {
				JSONObject currentObject = (JSONObject) current;
				currentObject.put(currentKey, value);
				currentKey = null;
				tryPush(value);
			} else {
				throw new IllegalStateException("key not specified for object value");
			}
		} else if (current instanceof JSONArray) {
			JSONArray currentArray = (JSONArray) current;
			currentArray.add(value);
			tryPush(value);
		} else {
			throw new IllegalStateException("calling object() while not in a json context");
		}
	}

	private void tryPush(Object value) {
		if (value instanceof JSONArray || value instanceof JSONObject) {
			stack.push((JSON) value);
		}
	}

	public JSONBuilder object() {
		if (stack.isEmpty()) {
			stack.push(new JSONObject());
		} else {
			addValue(new JSONObject());
		}
		return this;
	}
	
	public JSONBuilder array() {
		if (stack.isEmpty()) {
			stack.push(new JSONArray());
		} else {
			addValue(new JSONArray());
		}
		return this;
	}

	public JSONBuilder value(Object value) {
		if (stack.isEmpty()) {
			throw new IllegalStateException("calling value() while not in a json context");
		} else {
			addValue(value);
		}
		return this;
	}
	
	public JSONBuilder key(String key) {
		if (stack.isEmpty()) {
			throw new IllegalStateException("calling key() while not in a jsonobject context");
		} else {
			JSON current = stack.peek();
			if (current instanceof JSONObject) {
				if (currentKey == null) {
					currentKey = key;
				} else {
					throw new IllegalStateException("key already specified");
				}
			} else {
				throw new IllegalStateException("calling key() while not in a jsonobject context");
			}
		}
		return this;
	}
	
	public JSONBuilder end() {
		if (stack.isEmpty()) {
			throw new IllegalStateException("JSONBuilder not initialized");
		} else {
			if (stack.size() > 1) {
				currentKey = null;
				stack.pop();
			}
		}
		return this;
	}
	
	public JSON build() {
		if (stack.isEmpty()) {
			throw new IllegalStateException("JSONBuilder not initailzied");
		} else {
			JSON current;
			do {
				current = stack.pop();
			} while (!stack.isEmpty());
			return current;
		}
	}
}

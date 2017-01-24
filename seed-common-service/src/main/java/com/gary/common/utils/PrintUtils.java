package com.gary.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jdk.nashorn.internal.ir.debug.JSONWriter;

/**
 * Created by gary on 16/12/27.
 */
public class PrintUtils {

	public static void print(Object obj) {
		System.out.println(JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect));
	}

	public static void printPretty(Object obj) {
		System.out.println(JSON.toJSONString(obj, true));

	}
}

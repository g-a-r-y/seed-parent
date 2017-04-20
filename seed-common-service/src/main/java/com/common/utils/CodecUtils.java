package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CodecUtils {
	public static String urlEncode(String url) {
		return urlEncode(url, "utf-8");
	}

	public static String urlEncode(String url, String encoding) {
		try {
			return URLEncoder.encode(url, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}

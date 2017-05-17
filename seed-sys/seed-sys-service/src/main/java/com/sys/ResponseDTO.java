package com.sys;

import java.io.Serializable;

/**
 * Created by gary on 17/5/10.
 */
public class ResponseDTO implements Serializable {

	private int code;

	private String msg;

	private Object result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}

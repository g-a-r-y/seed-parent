package com.sys;

import java.io.Serializable;

/**
 * Created by gary on 17/5/10.
 */
public class AccessTokenResultDTO extends ResponseDTO {

	private String accessToken;

	private String refreshToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public static void main(String[] args) {
		if(new AccessTokenResultDTO() instanceof Serializable) {

		}
	}
}

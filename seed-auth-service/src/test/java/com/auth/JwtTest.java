package com.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by gary on 17/4/21.
 */
public class JwtTest {

	public static void main(String[] args) {
		SecretKey secretKey = MacProvider.generateKey(SignatureAlgorithm.HS256);
//		KeyFactory.getInstance("rsa").generatePrivate(new RSAPublicKeySpec())
//		String keyStr = RandomStringUtils.randomAlphanumeric(20);
//		System.out.println(keyStr);

		String keyStr = "8YC4uQkNEEroIzKtKu2S";

//		String token = getToken(keyStr);
//		System.out.println(token);

//		String geneToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMDAsIm5iZiI6MTQ5MjY3NDAxOX0.Rilk4KBsamLo9Ci_bDV3hf9EQDl-JewEIphpgf_zVIc";
//		validateToken(keyStr, geneToken);

		byte[] b1 = Base64Utils.decodeFromString("eyJhbGciOiJIUzI1NiJ9");
		System.out.println(new String(b1));

		System.out.println(new String(Base64Utils.decodeFromString("eyJ1c2VyX2lkIjoxMDAsIm5iZiI6MTQ5MjY3NDAxOX0")));

		System.out.println(new String(Base64Utils.decodeFromString("MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY")));

	}

	public static String getToken(String key) {
		Date expiration = new Date(LocalDateTime.now()
//				.plusSeconds(30)
//				.plusMinutes(10L)
//				.plusDays(1L)
				.minusDays(1L)
				.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		String token = Jwts.builder()
				.claim("user_id", 100)
//				.setExpiration(expiration)
				.setNotBefore(expiration)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
		return token;
	}

	public static void validateToken(String key, String token) {
		Claims body = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token).getBody();
		Integer userId = (Integer) body.get("user_id");
		Date expiration1 = body.getExpiration();
		System.out.println(userId);
	}
}

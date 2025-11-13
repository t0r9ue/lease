package com.lease.common.util;

import com.lease.common.exception.LeaseException;
import com.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/9
 */
public class JwtUtil {

	private final static long tokenExpiration = 60 * 60 * 1000 * 24 *365L;

	private final static SecretKey secretKey = Keys.hmacShaKeyFor("u38ml5GAWYWqdzXWksCkSDloSKQ678q5".getBytes());

	public static String createToken(Long userId, String username) {
		String jwt = Jwts.builder()
				.setSubject("LOGIN_USER")
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
				.claim("userId", userId)
				.claim("username", username)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
		return jwt;
	}

	public static Claims parseToken(String token) {
		if (!StringUtils.hasText(token)) {
			throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
		}
		try {
			JwtParser jwtParser = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build();
			return jwtParser.parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
		} catch (JwtException e) {
			throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
		}
	}

}

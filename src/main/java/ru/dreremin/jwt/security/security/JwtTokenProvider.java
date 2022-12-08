package ru.dreremin.jwt.security.security;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.token.secret}")
	private String secret;
	
	public String generateToken(String login) {
		
		Date expirationDate = Date.from(
				ZonedDateTime.now().plusMinutes(60).toInstant());
		return JWT.create()
				.withSubject("Actor details")
				.withClaim("login", login)       //что шифруем
				.withIssuedAt(new Date())        //время создания
				.withIssuer("jwt-security")      //кто выдал
				.withExpiresAt(expirationDate)   //время истечения
				.sign(Algorithm.HMAC256(secret));//подписываем токен с помощью секрета
	} //секрет нужен для того, чтобы никто посторонний не смог создать валидный токен
	
	public String validateTokenAndRetrieveClaim(String token) 
			throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
				.withSubject("Actor details")     //проверяем соответствие заголовка
				.withIssuer("jwt-security")       //проверяем соответствие секрета
				.build();
		
		DecodedJWT jwt = verifier.verify(token);  //получаем декодированный jwt
		return jwt.getClaim("login").asString();  //получаем логин
	}
}

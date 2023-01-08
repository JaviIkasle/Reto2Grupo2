package com.example.Reto2Grupo2.auth.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Reto2Grupo2.auth.persistence.Rol;
import com.example.Reto2Grupo2.auth.persistence.Trabajador;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	// asignamos tiempo de validez del token. A partir de ahi no podra utilizarlo.
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24
	// private static final long EXPIRE_DURATION = 45 * 1000; // 24
	// mas adelante veremos como actualizar sin volver a hacer login
	
	// con la siguiente linea asigna a la SECRET_KEY nuestro app.jwt.secret del application.properties
	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	private static final String USER_ID_CLAIM = "trabajadorId";
	private static final String ROL_CLAIM = "rol";
	
	
	//TODO no funcionan los .claim, deberia
	public String generateAccessToken(Trabajador trabajador) {
		// cuando generamos el token podemos meter campos custom que nos puedan ser utiles mas adelante.
		return Jwts.builder()
				.setSubject(String.format("%s",trabajador.getUsername()))
				.setIssuer("ADTDAM")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				// .claim("userId", user.getId()) // podriamos meter datos custom, u objetos custom. ojo con meter "user" por que tiene la password en el modelo 
				// y passwords no queremos enviar ni devolver
				//.claim(USER_ID_CLAIM, trabajador.getId())
				//.claim(ROL_CLAIM, trabajador.getRol())
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed");
		}
		
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Integer getUserId(String token) {
		Claims claims = parseClaims(token);
		return (Integer) claims.get(USER_ID_CLAIM);
		
	}
	
	public Rol getUserRol(String token){
		Claims claims = parseClaims(token);
		Object jsonObject = claims.get(ROL_CLAIM);
		Rol rol = (Rol)jsonObject;
		
		return rol;
//		List <Rol> roles ;	
//		try{
//			roles = jsonArrayToList(jsonObject, Rol.class);
//			return roles;
//		}catch (IOException e) {
//			return new ArrayList<Rol>();
//		}
	}
	//TODO no se tendria que hacer esto, ya que no tenemos lista de roles
//	public static <T> List<T> jsonArrayToList(Object json, Class<T>elementClass) throws IOException{
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonString = objectMapper.writeValueAsString(json);
//		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
//		return objectMapper.readValue(jsonString, listType);
//	}
	
	private Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
}
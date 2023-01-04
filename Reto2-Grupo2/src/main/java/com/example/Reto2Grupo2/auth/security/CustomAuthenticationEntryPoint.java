package com.example.Reto2Grupo2.auth.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
			// si el token siendo valido, el usuario no tiene permisos para realizar la correspondiente operacion
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization failed");
		}else {
			//esto si el token no es valido o se pide el token y no se envia
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization failed");

		}
	}

}

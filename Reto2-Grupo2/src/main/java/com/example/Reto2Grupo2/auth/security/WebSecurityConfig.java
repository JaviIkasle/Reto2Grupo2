package com.example.Reto2Grupo2.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Reto2Grupo2.rol.modelo.RolEnum;


@Configuration
public class WebSecurityConfig {
	
	@Autowired 
	private JwtTokenFilter jwtTokenFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests(
				(authz) ->
						authz								
						
						.requestMatchers("/api/auth/signup/empleados").hasAuthority(RolEnum.ADMIN.name())
						.requestMatchers("/api/auth/signup/admin").hasAuthority(RolEnum.ADMIN.name())
						.requestMatchers("/api/auth/signup/clientes").permitAll()					
						.requestMatchers("/api/auth/login").permitAll()
						
						
						.requestMatchers(HttpMethod.GET, "/api/eventos/**").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers("/api/eventos/**").hasAuthority(RolEnum.EMPLEADO.name())
						
						.requestMatchers(HttpMethod.GET, "/api/zoos/**").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name(), RolEnum.ADMIN.name())
						.requestMatchers("/api/zoos/**").hasAnyAuthority(RolEnum.ADMIN.name())
						
						.requestMatchers(HttpMethod.GET, "/api/animales/**").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers("/api/animales/**").hasAuthority(RolEnum.EMPLEADO.name())	
						
						.requestMatchers(HttpMethod.GET, "/api/especies/**").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers("/api/especies/**").hasAuthority(RolEnum.EMPLEADO.name())	
									
						.requestMatchers(HttpMethod.POST, "/api/billetes").hasAuthority(RolEnum.CLIENTE.name())
						.requestMatchers( "/api/billetes").denyAll() 											
												
						.requestMatchers("/api/users/cliente").hasAuthority(RolEnum.CLIENTE.name())
						.requestMatchers("/api/users/**").hasAuthority(RolEnum.ADMIN.name())
					
						.requestMatchers("/api/roles/**").hasAuthority(RolEnum.ADMIN.name())																														

						.requestMatchers("/api/roles/**").hasAuthority(RolEnum.ADMIN.name())																																	
						
						.anyRequest().authenticated()
						);		
		
		http.exceptionHandling().accessDeniedHandler(new CustomAccesDeniedHandler());
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);		
		return http.build();
	}
}

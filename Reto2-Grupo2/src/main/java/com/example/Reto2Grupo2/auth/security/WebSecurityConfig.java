package com.example.Reto2Grupo2.auth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.Reto2Grupo2.cifrados.RSA.OurPassEncoder;
import com.example.Reto2Grupo2.rol.modelo.RolEnum;

@CrossOrigin
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
		return new OurPassEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://localhost:7024", "http://localhost:3100"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must
		// not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests((authz) -> authz

				.requestMatchers("/api/auth/signup/empleados").hasAuthority(RolEnum.ADMIN.name())
				.requestMatchers("/api/auth/signup/admin").hasAuthority(RolEnum.ADMIN.name())
				.requestMatchers("/api/auth/signup/clientes").permitAll()
				.requestMatchers("/api/auth/signup/clientes/android").permitAll()
				.requestMatchers("/api/auth/login").permitAll()
				.requestMatchers("/api/auth/login/android").permitAll()
				.requestMatchers("/api/getPublicKey").permitAll()
				.requestMatchers("/api/users/clientePass/{email}").permitAll()

				.requestMatchers("/v3/api-docs").permitAll().requestMatchers("/v3/api-docs/**").permitAll()
				.requestMatchers("/swagger-ui").permitAll().requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/swagger-ui.html").permitAll()

				.requestMatchers(HttpMethod.GET, "/api/eventos/**").hasAnyAuthority(RolEnum.CLIENTE.name(), RolEnum.EMPLEADO.name())
				.requestMatchers("/api/eventos/**").hasAuthority(RolEnum.EMPLEADO.name())

				.requestMatchers(HttpMethod.GET, "/api/zoos/**").hasAnyAuthority(RolEnum.CLIENTE.name(), RolEnum.EMPLEADO.name(), RolEnum.ADMIN.name())
				.requestMatchers("/api/zoos/**").hasAnyAuthority(RolEnum.ADMIN.name())

				.requestMatchers(HttpMethod.GET, "/api/animales/**").hasAnyAuthority(RolEnum.CLIENTE.name(), RolEnum.EMPLEADO.name())
				.requestMatchers("/api/animales/**").hasAuthority(RolEnum.EMPLEADO.name())

				.requestMatchers(HttpMethod.GET, "/api/especies/**").hasAnyAuthority(RolEnum.CLIENTE.name(), RolEnum.EMPLEADO.name())
				.requestMatchers("/api/especies/**").denyAll()

				.requestMatchers(HttpMethod.POST, "/api/billetes").hasAuthority(RolEnum.CLIENTE.name())
				.requestMatchers("/api/billetes").denyAll()

				.requestMatchers("/api/users/cliente").hasAuthority(RolEnum.CLIENTE.name())
				.requestMatchers("/api/users/cliente/android").hasAuthority(RolEnum.CLIENTE.name())
				.requestMatchers("/api/users/**").hasAuthority(RolEnum.ADMIN.name())

				.requestMatchers("/api/roles/**").hasAuthority(RolEnum.ADMIN.name())

				.requestMatchers("/api/roles/**").hasAuthority(RolEnum.ADMIN.name())

				.requestMatchers("/api/usersWEB/**").permitAll()

				.anyRequest().authenticated());

		http.exceptionHandling().accessDeniedHandler(new CustomAccesDeniedHandler());
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
		return http.build();
	}
}

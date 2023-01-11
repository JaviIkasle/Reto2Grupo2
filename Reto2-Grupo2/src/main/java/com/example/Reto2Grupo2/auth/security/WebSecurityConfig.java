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

import com.example.Reto2Grupo2.auth.model.RolEnum;


@Configuration
public class WebSecurityConfig {
	

	@Autowired 
	private JwtTokenFilter jwtTokenFilter;

	// carga los detalles de usuario.
	// la validez de la contraseña es automatica. Si es incorrecta no se loguea y devuelve 401
//	@Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return userRepository.findByEmail(username)
//                        .orElseThrow(
//                                () -> new UsernameNotFoundException("User " + username + " not found"));
//            }
//        };
//    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	// utilizado para encriptar las contraseñas en la DB
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// aqui definimos cuales son las URLs que van a poder ser accesibles sin identificarse
	// y cuales no
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests(
				(authz) ->
						authz						
						.requestMatchers("/api/auth/signup/empleados").hasAuthority(RolEnum.ADMIN.name())
						.requestMatchers(HttpMethod.GET, "/api/eventos").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())// solo puede hacer get
						.requestMatchers(HttpMethod.GET, "/api/zoos").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers(HttpMethod.GET, "/api/animales").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers(HttpMethod.GET, "/api/especies").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name())
						.requestMatchers(HttpMethod.POST, "/api/billetes").hasAuthority(RolEnum.CLIENTE.name())
						.requestMatchers("/api/users/**").hasAuthority(RolEnum.ADMIN.name())
						.requestMatchers("/api/roles/**").hasAuthority(RolEnum.ADMIN.name())
						.requestMatchers("/api/eventos/**").hasAuthority(RolEnum.EMPLEADO.name())
						
						
						
						//.requestMatchers(HttpMethod.GET, "/api/billetes").hasAnyAuthority(RolEnum.CLIENTE.name(),RolEnum.EMPLEADO.name()) //EN PRINCIPIO NADIE PUEDE VER BILLETES
//						.requestMatchers(HttpMethod.GET,"/api/zoos").hasAuthority(RolEnum.EMPLEADO.name())
//						.requestMatchers(HttpMethod.GET,"/api/animales/**").hasAuthority(RolEnum.EMPLEADO.name())
//						.requestMatchers(HttpMethod.GET,"/api/especies/**").hasAuthority(RolEnum.EMPLEADO.name())
//						.requestMatchers(HttpMethod.PUT,"/api/users/**").hasAuthority(RolEnum.EMPLEADO.name())
						.requestMatchers("/api/auth/login").permitAll()		
						.requestMatchers("/api/auth/signup/clientes").permitAll()	
						
						
						.anyRequest().authenticated()
						);		
		
		http.exceptionHandling().accessDeniedHandler(new CustomAccesDeniedHandler());
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);		
		return http.build();
	}
}

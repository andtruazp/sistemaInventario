package com.app.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.app.inventario.service.UsuarioDetailService;


@Configuration
@EnableWebSecurity()
public class SecurityConfig {
	
	private final UsuarioDetailService usuarioDetailService;
	
	public SecurityConfig(UsuarioDetailService usuarioDetailService) {
        this.usuarioDetailService = usuarioDetailService;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/inventario/**").hasAnyAuthority("ver_inventario")
            .requestMatchers("/inventario/agregar", "/inventario/aumentar", "/inventario/baja").hasAuthority("agregar_producto")
            .requestMatchers("/salida/**").hasAuthority("ver_salida_productos")
            .requestMatchers("/historico/**").hasAuthority("ver_historico")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/auth/login")
            .defaultSuccessUrl("/dashboard")
            .failureUrl("/auth/login?error")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login?logout")
            .permitAll()
        )
        .exceptionHandling(exception -> exception
            .accessDeniedHandler(accessDeniedHandler())
        );

    return http.build();
	}
	
	@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/auth/acceso-denegado");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
    	return NoOpPasswordEncoder.getInstance();
    }

}

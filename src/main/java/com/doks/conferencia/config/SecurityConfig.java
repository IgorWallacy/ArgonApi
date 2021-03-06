package com.doks.conferencia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.doks.conferencia.service.UsuarioService;

@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService service;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth
				.userDetailsService(service)
				.passwordEncoder(passwordEncoder());

	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.cors()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}

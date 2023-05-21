package com.doks.conferencia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()

				.antMatchers("/actuator/**").permitAll()

				.antMatchers("/Lote/**").permitAll().antMatchers("/api_public/**").permitAll().antMatchers("/oauth/**")
				.permitAll().antMatchers("/api/**").authenticated().antMatchers("/api_bi/**").authenticated()

				.antMatchers("/api_vendas/**").authenticated().antMatchers("/api_vga/**").authenticated()
				.antMatchers("/api/pedido/**").authenticated()

				.antMatchers("/api_precificacao/**").authenticated().antMatchers("/api_react/**").authenticated()

				.antMatchers("/api/nota/**").authenticated().antMatchers("/api/lote/**").authenticated()
				.antMatchers("/api/produto/**").authenticated().antMatchers("/api/unidademedida/**").authenticated()
				.antMatchers("/api/filial/**").authenticated().antMatchers("/api/conferenciamanual/**").authenticated()
				.antMatchers("/api/entidade/**").authenticated().antMatchers("/api/formacaoprecoproduto/**")
				.authenticated()

				.anyRequest().denyAll();
	}

}

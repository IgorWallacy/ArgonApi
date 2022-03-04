package com.doks.conferencia.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.doks.conferencia.config.token.CustomTokenEnchancer;

@Configuration
@EnableAuthorizationServer
public class AuthorizantionServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${security.jwt.signing-key}")
	private String signingKey;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnchancer(), accessTokenConverter()));
		
		endpoints.tokenStore(tokenStore())
							.tokenEnhancer(tokenEnhancerChain)
							.accessTokenConverter(accessTokenConverter())
							.authenticationManager(authenticationManager);
	}
	
	@Bean
	public TokenEnhancer tokenEnchancer() {
		
		return new CustomTokenEnchancer();
	}


	private TokenStore tokenStore() {
				return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter () {
		
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		
		tokenConverter.setSigningKey(signingKey);
		
		return tokenConverter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		clients.inMemory().withClient("doks")
		.secret(b.encode("1234"))
		.scopes("read", "write")
		.authorizedGrantTypes("password")
		.accessTokenValiditySeconds(9600);
	}

}

package com.doks.conferencia.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.doks.conferencia.security.UsuarioSistema;

public class CustomTokenEnchancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		
		addInfo.put("id", usuarioSistema.getUsuario().getId());
		addInfo.put("nome", usuarioSistema.getUsuario().getNome());
		addInfo.put("supervisor", usuarioSistema.getUsuario().getSupervisor());
		
		
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		
		return accessToken;
	
		
		
	}

}

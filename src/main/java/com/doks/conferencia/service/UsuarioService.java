package com.doks.conferencia.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doks.conferencia.model.Usuario;
import com.doks.conferencia.repository.UsuarioRepository;
import com.doks.conferencia.security.UsuarioSistema;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuarioEncontrado = repository.findByCodigo(username).orElseThrow( () -> new UsernameNotFoundException("Login nao encontrado"));
		
		
	//	return User.builder().username(usuarioEncontrado.getCodigo()).password(usuarioEncontrado.getSenha()).roles("USER").build(); 
		
		
		
		
		
		return new UsuarioSistema(usuarioEncontrado, getPermissoes());
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		
		return authorities;
	}
	
	

}

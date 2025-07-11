package com.app.inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.inventario.model.Usuario;
import com.app.inventario.repository.UsuarioRepository;

@Service
public class UsuarioDetailService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByCorreo(correo)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		List<GrantedAuthority> authorities = usuario.getRol().getPermisos().stream()
				.map(permiso -> new SimpleGrantedAuthority(permiso.getPermiso()))
				.collect(Collectors.toList());
		
		return new User(usuario.getCorreo(), usuario.getContrasena(), authorities);
	}
}

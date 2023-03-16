package com.br.bestsolutions.backendbestsolutions.services;

import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import com.br.bestsolutions.backendbestsolutions.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User não encontrado com o email: " + email));

        return new User(
                usuario.getEmail(), usuario.getPassword(), Collections.emptyList());
    }
}
package com.br.bestsolutions.backendbestsolutions.controllers;

import com.br.bestsolutions.backendbestsolutions.dtos.JwtModelResponseDto;
import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import com.br.bestsolutions.backendbestsolutions.jwt.JwtResponse;
import com.br.bestsolutions.backendbestsolutions.jwt.JwtTokenUtils;
import com.br.bestsolutions.backendbestsolutions.models.JwtRequest;
import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import com.br.bestsolutions.backendbestsolutions.repositories.UsuarioRepository;
import com.br.bestsolutions.backendbestsolutions.services.JwtUsuarioService;
import com.br.bestsolutions.backendbestsolutions.services.UsuarioService;
import com.br.bestsolutions.backendbestsolutions.wrapers.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private JwtUsuarioService jwtUsuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioWrapper usuarioWrapper;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        userService.register(usuarioWrapper.toEntity(usuarioDto));
        final UserDetails userDetails = usuarioWrapper.toEntity(usuarioDto);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, usuarioDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = jwtUsuarioService.loadUserByUsername(authenticationRequest.getEmail());
        final Usuario usuario = usuarioRepository.findByEmail((authenticationRequest.getEmail())).orElseThrow();
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new JwtResponse(token, usuarioWrapper.toDto(usuario)));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        jwtTokenUtil.setInBlacklist(request.getHeader("Authorization").substring(7));
        return ResponseEntity.ok().body("Logout realizado com sucesso!");
    }

    private void authenticate(String email, String senha) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
        } catch (DisabledException e) {
            throw new Exception("USER DESABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVÁLIDAS", e);
        }
    }


}
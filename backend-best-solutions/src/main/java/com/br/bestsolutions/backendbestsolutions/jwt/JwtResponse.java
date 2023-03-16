package com.br.bestsolutions.backendbestsolutions.jwt;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String jwtToken;
    private final UsuarioDto usuarioDto;

    public JwtResponse(String jwtToken, UsuarioDto usuarioDto) {
        this.jwtToken = jwtToken;
        this.usuarioDto = usuarioDto;
    }

}
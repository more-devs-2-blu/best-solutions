package com.br.bestsolutions.backendbestsolutions.models;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtModelResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jwtToken;
    private Usuario usuario;
}

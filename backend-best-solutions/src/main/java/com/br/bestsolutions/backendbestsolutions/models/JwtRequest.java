package com.br.bestsolutions.backendbestsolutions.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;

}

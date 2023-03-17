package com.br.bestsolutions.backendbestsolutions.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_USUARIOS")
public class Usuario implements UserDetails {
    private static final long serialVersionUID = 1L;

    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID id;

    //Questão da autenticação
    private String nome;
    @Column(unique = true, nullable = false, updatable = false)
    private String email;
    @Column(nullable = false)
    private String senha;

    //Dados cadastrais
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime entrada;
    private String razaoSocial;
    private String nomeFantasia;
    private String responsavel;
    private String naturezaJuridica;
    private String enquadramento;
    private String tipoTributacao;
    private Integer capital;
    private String tipoServico;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String inscricaoIptu;
    private Double areaTotal;
    private Double areaImovel;
    private String cpfProprietario;
    private String situacaoCadastral;
    private String nomeSocios;
    private String socioPrincipal;
    private String cnae;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

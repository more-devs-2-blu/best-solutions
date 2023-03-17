package com.br.bestsolutions.backendbestsolutions.dtos;

import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
public class UsuarioDto implements Serializable {


    private String nome;
    @Email
    private String email;
    private String senha;
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
    @CPF
    private String cpfProprietario;
    private String situacaoCadastral;
    private String nomeSocios;
    private String socioPrincipal;
    private String cnae;

    //Getters e setters


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setNaturezaJuridica(String naturezaJuridica) {
        this.naturezaJuridica = naturezaJuridica;
    }

    public void setEnquadramento(String enquadramento) {
        this.enquadramento = enquadramento;
    }

    public void setTipoTributacao(String tipoTributacao) {
        this.tipoTributacao = tipoTributacao;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setInscricaoIptu(String inscricaoIptu) {
        this.inscricaoIptu = inscricaoIptu;
    }

    public void setAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public void setAreaImovel(Double areaImovel) {
        this.areaImovel = areaImovel;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public void setSituacaoCadastral(String situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public void setNomeSocios(String nomeSocios) {
        this.nomeSocios = nomeSocios;
    }

    public void setSocioPrincipal(String socioPrincipal) {
        this.socioPrincipal = socioPrincipal;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getNaturezaJuridica() {
        return naturezaJuridica;
    }

    public String getEnquadramento() {
        return enquadramento;
    }

    public String getTipoTributacao() {
        return tipoTributacao;
    }

    public Integer getCapital() {
        return capital;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getInscricaoIptu() {
        return inscricaoIptu;
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public Double getAreaImovel() {
        return areaImovel;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public String getNomeSocios() {
        return nomeSocios;
    }

    public String getSocioPrincipal() {
        return socioPrincipal;
    }

    public String getCnae() {
        return cnae;
    }
}

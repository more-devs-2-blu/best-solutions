package com.br.bestsolutions.backendbestsolutions.wrapers;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioWrapper {

    public UsuarioDto toDto(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        BeanUtils.copyProperties(usuario, usuarioDto);
        return usuarioDto;
    }

    public Usuario toEntity(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return usuario;
    }

    public List<UsuarioDto> toDtoList(List<Usuario> usuarios){
        final List<UsuarioDto> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDto.add(toDto(usuario));
        }
        return usuariosDto;
    }

    public List<Usuario> toEntityList(List<UsuarioDto> usuariosDto){
        final List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioDto usuarioDto : usuariosDto) {
            usuarios.add(toEntity(usuarioDto));
        }
        return usuarios;
    }
}

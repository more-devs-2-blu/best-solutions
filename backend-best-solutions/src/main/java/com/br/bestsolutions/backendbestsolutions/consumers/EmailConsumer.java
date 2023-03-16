package com.br.bestsolutions.backendbestsolutions.consumers;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import com.br.bestsolutions.backendbestsolutions.services.UsuarioService;
import com.br.bestsolutions.backendbestsolutions.wrapers.UsuarioWrapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailConsumer {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioWrapper usuarioWrapper;


    @RabbitListener
    public void listen(@Payload UsuarioDto usuarioDto){
        Usuario usuario = usuarioWrapper.toEntity(usuarioDto);
        usuario.setEntrada(LocalDateTime.from(LocalDateTime.now()));
        usuario.setSituacaoCadastral("Em análise");
        if(usuario.getNomeSocios() == null || usuario.getNomeSocios() == ""){
            usuario.setNomeSocios("Não há sócios");
            usuario.setSocioPrincipal("Não há sócios");
        }
        usuarioService.enviarEmail(usuario);
    }
}

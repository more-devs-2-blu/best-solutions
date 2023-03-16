package com.br.bestsolutions.backendbestsolutions.controllers;

import com.br.bestsolutions.backendbestsolutions.dtos.UsuarioDto;
import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import com.br.bestsolutions.backendbestsolutions.services.UsuarioService;
import com.br.bestsolutions.backendbestsolutions.wrapers.UsuarioWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioWrapper usuarioWrapper;


    @PostMapping
    public ResponseEntity<?> saveSolutions(@RequestBody
                                           @Valid
                                           UsuarioDto usuarioDto) {
        Usuario usuario = usuarioWrapper.toEntity(usuarioDto);
        usuario.setEntrada(LocalDateTime.from(LocalDateTime.now()));
        usuario.setSituacaoCadastral("Em análise");
        String entradaMail = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        if (usuario.getNomeSocios() == null || usuario.getNomeSocios() == "") {
            usuario.setNomeSocios("Não há sócios");
            usuario.setSocioPrincipal("Não há sócios");
        }


        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá " + usuarioDto.getResponsavel() + ", a sua solicatação para abertura da empresa: " + usuarioDto.getNomeFantasia() +
                " foi recebida em " + entradaMail + ". \nNo momento, a situação cadastral é: Em análise" +
                ". Você será informado assim que houver novas atualizações.");
        message.setTo(usuarioDto.getEmail());
        message.setFrom("bestsolutionsdevs@gmail.com");
        message.setSubject("Abertura de empresa");

        try {
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllSolutions() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getIdSolutions(@PathVariable(value = "id") UUID id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());

    }
    @GetMapping(value = "download/{id}")
    public ResponseEntity<Object>getDownload(@PathVariable(value = "id") UUID id) throws IOException, MessagingException, MessagingException {
        Optional<Usuario> solutionsModeloOptional = usuarioService.findById(id);

        if (!solutionsModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        usuarioService.gerarPdf(solutionsModeloOptional.get().getResponsavel(),solutionsModeloOptional.get().getRazaoSocial(),solutionsModeloOptional.get().getNomeFantasia(),
                solutionsModeloOptional.get().getEmail(),solutionsModeloOptional.get().getNaturezaJuridica(),solutionsModeloOptional.get().getEnquadramento(),solutionsModeloOptional.get().getCep(),
                solutionsModeloOptional.get().getEstado(),solutionsModeloOptional.get().getCidade(),solutionsModeloOptional.get().getBairro(),solutionsModeloOptional.get().getEndereco(),
                solutionsModeloOptional.get().getNumero(),solutionsModeloOptional.get().getCnae(),solutionsModeloOptional.get().getAreaTotal());
        String conteudo = ("Olá "+solutionsModeloOptional.get().getResponsavel()+ ", o seu documento de cadastro está em anexo. Agradeçemos pela preferência.");
        usuarioService.enviarEmailAnexo(solutionsModeloOptional.get().getEmail(),conteudo,"Documento para abertura de empresa","Recursos//Cadastro.pdf");

        return ResponseEntity.status(HttpStatus.OK).body("Documento baixado com sucesso");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> atualizaSolutions(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        System.out.println("O ID é : "+ id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (usuarioDto.getSituacaoCadastral() == null) {
            usuarioDto.setSituacaoCadastral(usuarioOptional.get().getSituacaoCadastral());
        }

        BeanUtils.copyProperties(usuarioDto, usuario);
        if (!usuarioDto.getSituacaoCadastral().equals("Em análise")) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Olá " + usuarioDto.getResponsavel() + ", houve uma atualização da situação cadastral da sua empresa: " + usuarioDto.getNomeFantasia() +
                    " para " + usuarioDto.getSituacaoCadastral() + ".");
            message.setTo(usuarioDto.getEmail());
            message.setFrom("bestsolutionsdevs@gmail.com");
            message.setSubject("Abertura de empresa");
            try {
                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        usuario.setId(usuarioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSolutions(@PathVariable(value = "id") UUID id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        } else {
            usuarioService.delete(usuarioOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

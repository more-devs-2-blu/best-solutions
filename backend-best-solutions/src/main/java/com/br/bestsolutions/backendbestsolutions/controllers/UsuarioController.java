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
    public ResponseEntity<List<UsuarioDto>> getAllSolutions() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioWrapper.toDtoList(usuarioService.findAll()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getIdSolutions(@PathVariable(value = "id") UUID id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);

        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());

    }

    @GetMapping(value = "download/{email}")
    public ResponseEntity<Object> getDownload(@PathVariable(value = "email") String email) throws IOException, MessagingException, MessagingException {
        Optional<Usuario> solutionsModeloOptional = usuarioService.findByEmail(email);

        if (!solutionsModeloOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        usuarioService.gerarPdf(solutionsModeloOptional.get().getResponsavel(), solutionsModeloOptional.get().getRazaoSocial(), solutionsModeloOptional.get().getNomeFantasia(),
                solutionsModeloOptional.get().getEmail(), solutionsModeloOptional.get().getNaturezaJuridica(), solutionsModeloOptional.get().getEnquadramento(), solutionsModeloOptional.get().getCep(),
                solutionsModeloOptional.get().getEstado(), solutionsModeloOptional.get().getCidade(), solutionsModeloOptional.get().getBairro(), solutionsModeloOptional.get().getEndereco(),
                solutionsModeloOptional.get().getNumero(), solutionsModeloOptional.get().getCnae(), solutionsModeloOptional.get().getAreaTotal());
        String conteudo = ("Olá " + solutionsModeloOptional.get().getResponsavel() + ", o seu documento de cadastro está em anexo. Agradeçemos pela preferência.");
        usuarioService.enviarEmailAnexo(solutionsModeloOptional.get().getEmail(), conteudo, "Documento para abertura de empresa", "Recursos//Cadastro.pdf");

        return ResponseEntity.status(HttpStatus.OK).body("Documento baixado com sucesso");
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<Object> atualizaSolutions(@PathVariable(value = "email") String email, @RequestBody @Valid UsuarioDto usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        System.out.println("O Email é : " + email);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (usuarioDto.getSituacaoCadastral() == null) {
            usuarioDto.setSituacaoCadastral(usuarioOptional.get().getSituacaoCadastral());
        }

        this.updateUsuario(usuarioDto, usuario);
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
        return ResponseEntity.status(HttpStatus.OK).body(usuarioWrapper.toDto(usuarioService.save(usuario)));
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Object> deleteSolutions(@PathVariable(value = "email") String email) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        System.out.println(usuarioOptional);
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado");
        } else {
            usuarioService.delete(usuarioOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public void updateUsuario(UsuarioDto chegando, Usuario novo) {
        if (chegando.getNome() != null) {
            novo.setNome(chegando.getNome());
        }
        if (chegando.getEmail() != null) {
            novo.setEmail(chegando.getEmail());
        }
        if (chegando.getSenha() != null) {
            novo.setSenha(chegando.getSenha());
        }
        if (chegando.getEntrada() != null) {
            novo.setEntrada(chegando.getEntrada());
        }
        if (chegando.getRazaoSocial() != null) {
            novo.setRazaoSocial(chegando.getRazaoSocial());
        }
        if (chegando.getNomeFantasia() != null) {
            novo.setNomeFantasia(chegando.getNomeFantasia());
        }
        if (chegando.getResponsavel() != null) {
            novo.setResponsavel(chegando.getResponsavel());
        }
        if (chegando.getNaturezaJuridica() != null) {
            novo.setNaturezaJuridica(chegando.getNaturezaJuridica());
        }
        if (chegando.getEnquadramento() != null) {
            novo.setEnquadramento(chegando.getEnquadramento());
        }
        if (chegando.getTipoTributacao() != null) {
            novo.setTipoTributacao(chegando.getTipoTributacao());
        }
        if (chegando.getTipoServico() != null) {
            novo.setTipoServico(chegando.getTipoServico());
        }
        if (chegando.getCep() != null) {
            novo.setCep(chegando.getCep());
        }
        if (chegando.getEstado() != null) {
            novo.setEstado(chegando.getEstado());
        }
        if (chegando.getCidade() != null) {
            novo.setCidade(chegando.getCidade());
        }
        if (chegando.getBairro() != null) {
            novo.setBairro(chegando.getBairro());
        }
        if (chegando.getEndereco() != null) {
            novo.setEndereco(chegando.getEndereco());
        }
        if (chegando.getComplemento() != null) {
            novo.setComplemento(chegando.getComplemento());
        }
        if (chegando.getInscricaoIptu() != null) {
            novo.setInscricaoIptu(chegando.getInscricaoIptu());
        }
        if (chegando.getCpfProprietario() != null) {
            novo.setCpfProprietario(chegando.getCpfProprietario());
        }
        if (chegando.getSituacaoCadastral() != null) {
            novo.setSituacaoCadastral(chegando.getSituacaoCadastral());
        }
        if (chegando.getNomeSocios() != null) {
            novo.setNomeSocios(chegando.getNomeSocios());
        }
        if (chegando.getSocioPrincipal() != null) {
            novo.setSocioPrincipal(chegando.getSocioPrincipal());
        }
        if (chegando.getCnae() != null) {
            novo.setCnae(chegando.getCnae());
        }
        if (chegando.getCapital() != null){
            novo.setCapital(chegando.getCapital());
        }
        if (chegando.getNumero() != null){
            novo.setNumero(chegando.getNumero());
        }
    }
}

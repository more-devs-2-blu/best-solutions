import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MessageService } from 'primeng/api';

import { UsuariosService } from 'src/app/usuario.service'
import { JwtRequest } from '../jwt-request';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {

  constructor(private usuarioService: UsuariosService, private messageService: MessageService) {  }

  registrar(registro: NgForm) : void{
    alert("Oi")
    let usuario: JwtRequest = registro.value;
    this.usuarioService.registrar(usuario).subscribe(
      (response) => {
        sessionStorage.setItem("token", response.jwtToken); //Guardando o token na sessão.
        sessionStorage.setItem("usuario", response.usuarioDto.email);
        this.addMessage("login-toast", "success", "Cadastro efetuado com sucesso!", "");
      },
      (error: HttpErrorResponse) => {
        this.addMessage("login-toast", "warning", "Ocorreu um erro ao realizar o cadastro!", "Detalhes do sistema: " + error.message);
        console.log(error);
        
      }
    )
  }

  addMessage(key: string, severity: string, summary: string, detail: string){
    this.messageService.add({key: key, severity: severity, summary: summary, detail: detail});
  }

  clear() {
    this.messageService.clear();
  }





}

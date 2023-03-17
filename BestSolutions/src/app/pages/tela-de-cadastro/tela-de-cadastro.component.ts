import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { UsuariosService } from 'src/app/usuario.service';

@Component({
  selector: 'app-tela-de-cadastro',
  templateUrl: './tela-de-cadastro.component.html',
  styleUrls: ['./tela-de-cadastro.component.css'],
  providers: [MessageService]
})
export class TelaDeCadastroComponent implements AfterViewInit{

  constructor (private usuarioService: UsuariosService, private messageService: MessageService) { }


  ngAfterViewInit(): void {
    this.usuarioService.getUsuarios().subscribe(
      (resposa) => {
        console.log(resposa);
        
      }
    )
  }

  cadastrarEmpresa(form: NgForm){
    
    this.usuarioService.editar(form.value).subscribe(
      (response)=> {
        console.log(response);
        this.addMessage("telacad-toast", "success", "Atualizações realizadas com sucesso!", "");
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        this.addMessage("telacad-toast", "error", "Houve algum problema nas atualizações", error.message);
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

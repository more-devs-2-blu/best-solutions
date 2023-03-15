import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ScrollPanel } from 'primeng/scrollpanel';
import { Pergunta } from './pergunta';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  @ViewChild('scrollPanelRef', { static: false }) scrollPanelRef: ScrollPanel;

  //Display da janela modal (true = aparente, false = sumida)
  display: boolean = false;
  //Histórico de perguntas e respostas do chat
  mensagens: Pergunta[] = [];

  //Perguntas iniciais (pré definidas)
  perguntas: Pergunta[] = [
    { id: 1, pergunta: "Pergunta 1", resposta: "Resposta 1", dono: "System" },
    { id: 2, pergunta: "Pergunta 2", resposta: "Resposta 2", dono: "System" },
    { id: 3, pergunta: "Pergunta 3", resposta: "Resposta 3", dono: "System" },
    { id: 4, pergunta: "Pergunta 4", resposta: "Resposta 4", dono: "System" },
    { id: 5, pergunta: "Pergunta 5", resposta: "Resposta 5", dono: "System" }
  ];

  /**
   * Este método é acionado com o clique do botão do input quando o
   * usuário envia o número referente a pergunta que ele escolheu.
   * O sistema verifica a pergunta, pega a resposta e adiciona as
   * 2 informações na lista de mensagens.
   */
  pesquisarResposta(form: NgForm) {
    this.mensagens.push({ id: this.mensagens.length, dono: "User", pergunta: null, resposta: `Você digitou a opção: ${form.value.id}` });
    this.mensagens.push({ id: this.mensagens.length, dono: "System", pergunta: this.perguntar(form.value.id), resposta: null });
  }

  /**
   * Esta função fica responsável por controlar a visibilidade da janela modal.
   */
  showDialog() {
    if (this.display) {
      this.display = false;
      this.mensagens = [];
    } else {
      this.display = true;
    }
  }

  /**
   * Esta função retorna a resposta referente a uma pergunta através do seu ID.
   */
  private perguntar(id: number): string {
    for (let pergunta of this.perguntas) {
      if (pergunta.id == id && pergunta.resposta != null) {
        return pergunta.resposta;
      }
    }
    return "Por gentileza selecione uma opção válida!";
  }
}

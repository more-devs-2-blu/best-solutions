import { TelaDeCadastroComponent } from './pages/tela-de-cadastro/tela-de-cadastro.component';
import { TableModule } from 'primeng/table';
import { MegaMenuModule } from 'primeng/megamenu';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NavbarComponent } from './sharepage/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { EtapasComponent } from './pages/etapas/etapas.component';
import { CadastrarEmpresaComponent } from './pages/cadastrar-empresa/cadastrar-empresa.component';
import { LoginComponent } from './login/login.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { TooltipModule } from 'primeng/tooltip';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { FormsModule }   from '@angular/forms';
import {PasswordModule} from 'primeng/password';
import { ChecklistComponent } from './pages/checklist/checklist.component';
import {ListboxModule} from 'primeng/listbox';
import {MessageModule} from 'primeng/message';




@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    AboutComponent,
    CadastrarEmpresaComponent,
    EtapasComponent,
    LoginComponent,
    ChatbotComponent,
    ChecklistComponent,
    TelaDeCadastroComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    MegaMenuModule,
    RouterModule,
    AppRoutingModule,
    TableModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    CardModule,
    TooltipModule,
    ScrollPanelModule,
    FormsModule,
    PasswordModule,
    ListboxModule,
    MessageModule

  ],
  providers: [NgForm],
  bootstrap: [AppComponent]
})
export class AppModule { }

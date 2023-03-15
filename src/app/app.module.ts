
import { TableModule } from 'primeng/table';

import { MegaMenuModule } from 'primeng/megamenu';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './sharepage/navbar/navbar.component';
import { MenuComponent } from './pages/menu/menu.component';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
// import { EtapasComponent } from './pages/etapas/etapas.component'


import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { EtapasComponent } from './pages/etapas/etapas.component';
import { CadastrarEmpresaComponent } from './pages/cadastrar-empresa/cadastrar-empresa.component';
import { TelaDeCadastroComponent } from './pages/tela-de-cadastro/tela-de-cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    MenuComponent,
    HomeComponent,
    AboutComponent,
    CadastrarEmpresaComponent,
    EtapasComponent,
    TelaDeCadastroComponent


  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    MegaMenuModule,
    RouterModule,
    AppRoutingModule,
    TableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

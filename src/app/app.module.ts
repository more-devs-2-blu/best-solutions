
import { TableModule } from 'primeng/table';

import { MegaMenuModule } from 'primeng/megamenu';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './sharepage/navbar/navbar.component';
import { FooterComponent } from './sharepage/footer/footer.component';
import { MenuComponent } from './pages/menu/menu.component';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
// import { EtapasComponent } from './pages/etapas/etapas.component'


import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { EtapasComponent } from './etapas/etapas.component';
import { CadastrarEmpresaComponent } from './cadastrar-empresa/cadastrar-empresa.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    MenuComponent,
    HomeComponent,
    AboutComponent,
    CadastrarEmpresaComponent,
    EtapasComponent


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

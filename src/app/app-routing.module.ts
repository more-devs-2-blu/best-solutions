import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { EtapasComponent } from './pages/etapas/etapas.component';
import { AboutComponent } from './pages/about/about.component';
import { MenuComponent } from './pages/menu/menu.component';
import { HomeComponent } from './pages/home/home.component';
import { EtapasComponent } from './etapas/etapas.component';
import { CadastrarEmpresaComponent } from './cadastrar-empresa/cadastrar-empresa.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'menu', component:MenuComponent},
  {path:'about', component:AboutComponent},
  {path:'etapas', component:EtapasComponent},
  {path:'cadastrarEmpresa', component:CadastrarEmpresaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


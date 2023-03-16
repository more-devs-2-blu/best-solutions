
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './pages/about/about.component';
import { HomeComponent } from './pages/home/home.component';
import { EtapasComponent } from './pages/etapas/etapas.component';
import { CadastrarEmpresaComponent } from './pages/cadastrar-empresa/cadastrar-empresa.component';
import { TelaDeCadastroComponent } from './pages/tela-de-cadastro/tela-de-cadastro.component';
import { ChecklistComponent } from './pages/checklist/checklist.component';


const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'about', component:AboutComponent},
  {path:'etapas', component:EtapasComponent},
  {path:'cadastrarEmpresa', component:CadastrarEmpresaComponent},
  {path:'telaDeCadastro', component:TelaDeCadastroComponent},
  {path:'checklist', component:ChecklistComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


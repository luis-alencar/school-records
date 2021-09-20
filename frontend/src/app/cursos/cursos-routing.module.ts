import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { CursosCadastroComponent } from './cursos-cadastro/cursos-cadastro.component';
import { CursosPesquisaComponent } from './cursos-pesquisa/cursos-pesquisa.component';

const routes: Routes = [
  { path: 'cursos', component: CursosPesquisaComponent },
  { path: 'cursos/novo', component: CursosCadastroComponent },
  { path: 'cursos/:codigo', component: CursosCadastroComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class CursosRoutingModule { }

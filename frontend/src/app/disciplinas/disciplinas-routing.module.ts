import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { DisciplinaCadastroComponent } from './disciplina-cadastro/disciplina-cadastro.component';
import { DisciplinasPesquisaComponent } from './disciplinas-pesquisa/disciplinas-pesquisa.component';

const routes: Routes = [
  { path: 'disciplinas', component: DisciplinasPesquisaComponent },
  { path: 'disciplinas/novo', component: DisciplinaCadastroComponent },
  { path: 'disciplinas/:codigo', component: DisciplinaCadastroComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class DisciplinasRoutingModule { }

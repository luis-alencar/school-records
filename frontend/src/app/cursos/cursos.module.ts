import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputMaskModule } from 'primeng/inputmask';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { DropdownModule} from "primeng/dropdown";


import { SharedModule } from './../shared/shared.module';
import { CursosPesquisaComponent } from './cursos-pesquisa/cursos-pesquisa.component';
import { CursosCadastroComponent } from './cursos-cadastro/cursos-cadastro.component';
import { CursosRoutingModule } from './cursos-routing.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    InputMaskModule,

    SharedModule,
    CursosRoutingModule,
    DropdownModule
  ],
  declarations: [
    CursosCadastroComponent,
    CursosPesquisaComponent
  ],
  exports: [
    CursosCadastroComponent,
    CursosPesquisaComponent
  ]
})
export class CursosModule { }

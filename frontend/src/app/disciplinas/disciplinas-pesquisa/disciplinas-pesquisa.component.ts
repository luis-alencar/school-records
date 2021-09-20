import { Title } from '@angular/platform-browser';
import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent, MessageService, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { DisciplinasService } from '../disciplinas.service';
import {DisciplinasFiltro} from "../../core/intefece";

@Component({
  selector: 'app-disciplinas-pesquisa',
  templateUrl: './disciplinas-pesquisa.component.html',
  styleUrls: ['./disciplinas-pesquisa.component.css']
})
export class DisciplinasPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new DisciplinasFiltro();
  disciplinas = [];
  @ViewChild('tabela') grid: Table;

  constructor(
    private disciplinaService: DisciplinasService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService,
    private confirmation: ConfirmationService,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Consultar disciplinas');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.disciplinaService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.disciplinas = resultado.disciplinas;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(disciplina: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(disciplina);
      }
    });
  }

  excluir(disciplina: any) {
    this.disciplinaService.excluir(disciplina.codigo)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.reset();
        }
        this.messageService.add({ severity: 'success', detail: 'Disciplina excluída com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}

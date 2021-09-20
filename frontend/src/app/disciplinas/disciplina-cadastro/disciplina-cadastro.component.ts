import { Title } from '@angular/platform-browser';
import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MessageService } from 'primeng/api';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';
import { CursosService } from '../../cursos/cursos.service';
import { DisciplinasService } from '../disciplinas.service';
import {Disciplina} from "../../core/intefece";

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './disciplina-cadastro.component.html',
  styleUrls: ['./disciplina-cadastro.component.css']
})
export class DisciplinaCadastroComponent implements OnInit {

  tipos = [
    { label: 'Ativo', value: true},
    { label: 'Inativo', value: false },
  ];

  cursos = [];
  disciplina = new Disciplina();

  constructor(
    private cursoService: CursosService,
    private diciplinaService: DisciplinasService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    const codigoDisciplina = this.route.snapshot.params['codigo'];

    this.title.setTitle('Registrar Disciplinas');

    if (codigoDisciplina) {
      this.carregarDisciplina(codigoDisciplina);
    }

    this.carregarCursos();
  }

  get editando() {
    return Boolean(this.disciplina.codigo)
  }

  carregarDisciplina(codigo: number) {
    this.diciplinaService.buscarPorCodigo(codigo)
      .then(disciplina => {
        this.disciplina = disciplina;
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizarDisciplina(form);
    } else {
      this.adicionarDisciplina(form);
    }
  }

  adicionarDisciplina(form: FormControl) {
    this.diciplinaService.adicionar(this.disciplina)
      .then(disciplinaAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Disciplina adicionada com sucesso!' });

        this.router.navigate(['/lancamentos', disciplinaAdicionado.codigo]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarDisciplina(form: FormControl) {
    this.diciplinaService.atualizar(this.disciplina)
      .then(disciplina => {
        this.disciplina = disciplina;

        this.messageService.add({ severity: 'success', detail: 'Disciplina alterada com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarCursos() {
    this.cursoService.listarTodas()
      .then(cursos => {
        this.cursos = cursos
          .map(p => ({ label: p.descricao, value: p.codigo }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  novo(form: FormControl) {
    form.reset();

    setTimeout(function() {
      this.disciplina = new Disciplina();
    }.bind(this), 1);

    this.router.navigate(['/disciplinas/novo']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de disciplina: ${this.disciplina.descricao}`);
  }

}

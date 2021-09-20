import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { MessageService } from 'primeng/api';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CursosService } from '../cursos.service';
import { Curso } from '../../core/intefece';

@Component({
  selector: 'app-curso-cadastro',
  templateUrl: './cursos-cadastro.component.html',
  styleUrls: ['./cursos-cadastro.component.css']
})
export class CursosCadastroComponent implements OnInit {

  curso = new Curso();
  ativos = [
    { label: 'Ativo', value: true },
    { label: 'Inativo', value: false },
  ];

  constructor(
    private cursoService: CursosService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    const codigoCurso = this.route.snapshot.params['codigo'];

    this.title.setTitle('Novo curso');

    if (codigoCurso) {
      this.carregarCurso(codigoCurso);
    }
  }

  get editando() {
    return Boolean(this.curso.codigo)
  }

  carregarCurso(codigo: number) {
    this.cursoService.buscarPorCodigo(codigo)
      .then(curso => {
        this.curso = curso;
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form: FormControl) {
    this.curso.dataCadastro = new Date();
    this.cursoService.adicionar(this.curso)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Curso adicionado com sucesso!' });

        form.reset();
        this.curso = new Curso();
      })
      .catch(erro => this.errorHandler.handle(erro));
    // if (this.editando) {
    //   this.atualizarCurso(form);
    // } else {
    //   this.adicionarCurso(form);
    // }
  }

  adicionarCurso(form: FormControl) {
    this.curso.dataCadastro = new Date();
    this.cursoService.adicionar(this.curso)
      .then(cursoAdicionada => {
        this.messageService.add({ severity: 'success', detail: 'Curso adicionada com sucesso!' });
        this.router.navigate(['/curso', cursoAdicionada.codigo]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarCurso(form: FormControl) {
    this.cursoService.atualizar(this.curso)
      .then(curso => {
        this.curso = curso;

        this.messageService.add({ severity: 'success', detail: 'Curso alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  // nova(form: FormControl) {
  //   form.reset();
  //
  //   setTimeout(function() {
  //     this.curso = new Curso();
  //   }.bind(this), 1);
  //
  //   this.router.navigate(['/cursos/novo']);
  // }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de curso: ${this.curso.descricao}`);
  }

}

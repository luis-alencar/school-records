import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import * as moment from 'moment';

import {Disciplina, DisciplinasFiltro} from '../core/intefece';


@Injectable()
export class DisciplinasService {

  disciplinasUrl = 'http://localhost:8080/disciplina';

  constructor(private http: HttpClient) { }

  pesquisar(filtro: DisciplinasFiltro): Promise<any> {
    let params = new HttpParams()
      .set('page', filtro.pagina.toString())
      .set('size', filtro.itensPorPagina.toString());

    if (filtro.descricao) {
      params = params.set('descricao', filtro.descricao);
    }

    return this.http.get(`${this.disciplinasUrl}?resumo`, { params })
      .toPromise()
      .then(response => {
        const disciplinas = response['content'];

        const resultado = {
          disciplinas,
          total: response['totalElements']
        };

        return resultado;
      });
  }

  excluir(codigo: number): Promise<void> {
    return this.http.delete(`${this.disciplinasUrl}/${codigo}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(disciplina: Disciplina): Promise<Disciplina> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    return this.http.post<Disciplina>(this.disciplinasUrl, disciplina, { headers })
      .toPromise();
  }

  atualizar(disciplina: Disciplina): Promise<Disciplina> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    return this.http.put<Disciplina>(`${this.disciplinasUrl}/${disciplina.codigo}`,disciplina, { headers })
      .toPromise()
      .then(response => {
        const disciplinaAlterado = response;

        this.converterStringsParaDatas([disciplinaAlterado]);

        return disciplinaAlterado;
      });
  }

  buscarPorCodigo(codigo: number): Promise<Disciplina> {

    return this.http.get<Disciplina>(`${this.disciplinasUrl}/${codigo}`)
      .toPromise()
      .then(response => {
        const disciplina = response;

        this.converterStringsParaDatas([disciplina]);

        return disciplina;
      });
  }

  private converterStringsParaDatas(disciplinas: Disciplina[]) {
    for (const disciplina of disciplinas) {
      disciplina.dataCadastro = moment(disciplina.dataCadastro,
        'YYYY-MM-DD').toDate();

      if (disciplina.dataCadastro) {
        disciplina.dataCadastro = moment(disciplina.dataCadastro,
          'YYYY-MM-DD').toDate();
      }
    }
  }

}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import {Curso, CursosFiltro} from '../core/intefece';


@Injectable()
export class CursosService {

  cursoUrl = 'http://localhost:8080/curso';

  constructor(private http: HttpClient) { }

  pesquisar(filtro: CursosFiltro): Promise<any> {
    let params = new HttpParams()
      .set('page', filtro.pagina.toString())
      .set('size', filtro.itensPorPagina.toString());

    if (filtro.descricao) {
      params = params.set('descricao', filtro.descricao);
    }
    if (filtro.localidade) {
      params = params.set('localidade', filtro.localidade);
    }

    return this.http.get(`${this.cursoUrl}`, { params })
      .toPromise()
      .then(response => {
        const cursos = response['content'];

        const resultado = {
          cursos,
          total: response['totalElements']
        };
        return resultado;
      })
  }

  listarTodas(): Promise<any> {
    const headers = new HttpHeaders()
    return this.http.get(this.cursoUrl, { headers })
      .toPromise()
      .then(response => response['content']);
  }

  excluir(codigo: number): Promise<void> {
    const headers = new HttpHeaders()
    return this.http.delete(`${this.cursoUrl}/${codigo}`, { headers })
      .toPromise()
      .then(() => null);
  }

  mudarStatus(codigo: number, ativo: boolean): Promise<void> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    return this.http.put(`${this.cursoUrl}/${codigo}/status`, ativo, { headers })
      .toPromise()
      .then(() => null);
  }

  adicionar(curso: Curso): Promise<Curso> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    return this.http.post<Curso>(this.cursoUrl, curso, { headers })
      .toPromise();
  }

  atualizar(curso: Curso): Promise<Curso> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json');

    return this.http.post<Curso>(this.cursoUrl, curso, { headers })
      .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Curso> {
    return this.http.get<Curso>(`${this.cursoUrl}/${codigo}`)
      .toPromise();
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa } from './empresa';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private readonly API ='http://localhost:8080/'
  constructor(private http: HttpClient) { }

  lista():Observable<Empresa[]>{
    return this.http.get<Empresa[]>(this.API + 'empresas').pipe(
    tap((lista)=> console.log('GET/EMPRESA',lista))
    );
    }

    public criar(empresa: Empresa):Observable<Empresa[]>{
    return this.http.post<Empresa[]>(this.API, empresa).pipe(
      tap((empresa)=> console.log('Criar Empresa.SERVICE ok', empresa))
    )
  }

  public delete(id: number): Observable<Empresa>{
    return this.http.delete<Empresa>(this.API).pipe(
      tap((empresa)=> console.log('Deletar EMPRESA.SERVICE ok', empresa))
    )
  }
}

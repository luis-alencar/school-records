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
}

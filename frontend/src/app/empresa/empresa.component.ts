import { EmpresaService } from './empresa.service';
import { Component, OnInit } from '@angular/core';
import { Empresa } from './empresa';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {
  Empresa: Empresa[] = [];
  empresaValue!: FormGroup;

  constructor(
    //private fb: FormBuilder,
    private router: Router,
    private activeteRoute: ActivatedRoute,
    private empresaService: EmpresaService) { }

  ngOnInit(): void {
    this.ListAll();

    const id = this.activeteRoute.snapshot.params['id'];
    console.log('ID ', id)

    // this.empresaValue = this.fb.group({
    //   id:[''],
    //   nome:[''],
    //   projeto:['']
    // })
  }

  private ListAll() {
    this.empresaService.lista().subscribe((result) => {
      this.Empresa = result;
      console.log('dados retornados', result);
    });
  }

  criarEmpresa(){
     this.empresaService.criar(this.empresaValue).subscribe((result) => {
     //});
    //this.empresaValue.reset();
  }


  removerEmpresa(id: number) {
      this.empresaService.delete(id).subscribe((result) => {
        window.alert('Tarefa removida com sucesso!');
        this.ListAll();
        location.assign('/empresas')
      });
  }

}

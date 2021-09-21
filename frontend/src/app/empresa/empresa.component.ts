import { EmpresaService } from './empresa.service';
import { Component, OnInit } from '@angular/core';
import { Empresa } from './empresa';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {
  Empresa: Empresa[] = [];
  public empresaValue!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private empresaService: EmpresaService) { }

  ngOnInit(): void {
    this.ListAll();

    this.empresaValue = this.fb.group({
      id:[''],
      nome:[''],
      projeto:['']
    })
  }

  private ListAll() {
    this.empresaService.lista().subscribe((result) => {
      this.Empresa = result;
      console.log('dados retornados', result);
    });
  }



  private criarEmpresa(){
    this.empresaService.criar(this.empresaValue).subscribe((result) => {
    });
    this.empresaValue.reset();
  }
}

import { EmpresaService } from './empresa.service';
import { Component, OnInit } from '@angular/core';
import { Empresa } from './empresa';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {
  Empresa: Empresa[] = [];

  constructor(private empresaService: EmpresaService) { }

  ngOnInit(): void {
    this.ListAll();
  }

  private ListAll() {
    this.empresaService.lista().subscribe((result) => {
      this.Empresa = result;
      console.log('dados retornados', result);
    });
  }
}

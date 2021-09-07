package br.pids.records.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pids.records.dto.EmpresaDTO;
import br.pids.records.model.Empresa;
import br.pids.records.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService service;
	
	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> findAll(){
		List<EmpresaDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<EmpresaDTO>findById(@PathVariable Long id){
		Empresa obj = service.findById(id); 
		return ResponseEntity.ok().body(new EmpresaDTO(obj));
	}
}

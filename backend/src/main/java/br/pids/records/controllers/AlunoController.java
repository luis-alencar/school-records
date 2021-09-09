package br.pids.records.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pids.records.dto.AlunoDTO;
import br.pids.records.model.Aluno;
import br.pids.records.service.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll(){
		List<AlunoDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AlunoDTO>findById(@PathVariable Long id){
		Aluno obj = service.findById(id); 
		return ResponseEntity.ok().body(new AlunoDTO(obj));
	}
	
}

package br.pids.records.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pids.records.dto.EmpresaDTO;
import br.pids.records.model.Comentario;
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
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody EmpresaDTO objDto){
		Empresa obj = service.fromDTO(objDto);
		obj = service.insertEmpresa(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmpresaDTO> update (@PathVariable Long id, @RequestBody EmpresaDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/comentarios")
	public ResponseEntity<List<Comentario>>findComentarios(@PathVariable Long id){
		Empresa obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getComentario());
	}
}

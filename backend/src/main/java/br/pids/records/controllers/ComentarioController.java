package br.pids.records.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pids.records.dto.ComentarioDTO;
import br.pids.records.service.ComentarioService;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioController {
	
	@Autowired
	private ComentarioService service;
	
	@GetMapping
	public ResponseEntity<Page<ComentarioDTO>> findAll(Pageable pageable){
		Page<ComentarioDTO> list = service.findAll(pageable);
		return ResponseEntity.ok(list);
	}

}

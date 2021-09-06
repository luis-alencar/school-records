package br.pids.records.dto;

import java.io.Serializable;

import br.pids.records.model.Empresa;
import lombok.Data;

public @Data class EmpresaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	public EmpresaDTO(Empresa entity) {
		id = entity.getId();
		nome = entity.getNome();
	}
}

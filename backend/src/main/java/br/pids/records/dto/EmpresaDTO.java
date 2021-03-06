package br.pids.records.dto;

import java.io.Serializable;

import com.sun.istack.NotNull;

import br.pids.records.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class EmpresaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String projeto;
	
	public EmpresaDTO(Empresa entity) {
		id = entity.getId();
		nome = entity.getNome();
		projeto = entity.getProjeto();
		
	}
}

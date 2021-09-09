package br.pids.records.dto;

import java.io.Serializable;

import br.pids.records.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class AlunoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private int telefone;
	
	private EmpresaDTO empresa;
	
	public AlunoDTO(Aluno entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		telefone = entity.getTelefone();
		empresa = new EmpresaDTO(entity.getEmpresa());
	}
}

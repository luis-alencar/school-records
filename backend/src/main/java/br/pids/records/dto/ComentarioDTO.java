package br.pids.records.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.pids.records.model.Comentario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class ComentarioDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private LocalDate data;
	private String comentario;
	private int nota;
	private int tipo;
	
	private EmpresaDTO empresa;
	
	public ComentarioDTO(Comentario entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		data = entity.getData();
		comentario = entity.getComentario();
		nota = entity.getNota();
		tipo = entity.getTipo();
		empresa = new EmpresaDTO(entity.getEmpresa());
	}
}

package br.pids.records.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_comentario")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String titulo;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	@Lob
	@NotNull
	private String comentario;
	private int nota;
	private int tipo;
	
	@ManyToOne
	@JoinColumn(name ="empresa_id_comentario", referencedColumnName = "id")
	private Empresa empresa; 
	
}

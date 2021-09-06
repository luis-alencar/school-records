package br.pids.records.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_comentario")
public @Data class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private LocalDate data;
	private String comentario;
	private int nota;
	private int tipo;
	
	@ManyToOne
	@JoinColumn(name ="empresa_id_comentario")
	private Empresa empresa; 
	
}

package br.pids.records.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private String titulo;
	private LocalDate data;
	private String comentario;
	private int nota;
	private int tipo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="empresa_id_comentario")
	private Empresa empresa; 
	
}

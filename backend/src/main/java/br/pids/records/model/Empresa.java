package br.pids.records.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_empresa")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String projeto;	
	
	public Empresa(Long id, String nome, String projeto) {
		this.id = id;
		this.nome = nome;
		this.projeto = projeto;
	}

	@OneToMany(mappedBy = "empresa")
	private List<Aluno> aluno = new ArrayList<>();
	
	@OneToMany(mappedBy = "empresa")
	private List<Comentario> comentario = new ArrayList<>();
	
	
	
}

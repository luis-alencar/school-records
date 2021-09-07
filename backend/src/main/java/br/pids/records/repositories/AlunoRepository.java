package br.pids.records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pids.records.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}

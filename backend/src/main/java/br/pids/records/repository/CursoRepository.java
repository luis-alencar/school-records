package br.pids.records.repository;


import br.pids.records.repository.curso.CursoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pids.records.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>, CursoRepositoryQuery {
    
}

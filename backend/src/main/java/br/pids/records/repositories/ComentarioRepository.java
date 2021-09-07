package br.pids.records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pids.records.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}

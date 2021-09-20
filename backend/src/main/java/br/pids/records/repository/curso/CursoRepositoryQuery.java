package br.pids.records.repository.curso;

import br.pids.records.repository.projection.ResumoCurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.pids.records.model.Curso;
import br.pids.records.repository.filter.CursoFilter;

public interface CursoRepositoryQuery {
    public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable);
    public Page<ResumoCurso> resumir(CursoFilter cursoFilter, Pageable pageable);
}

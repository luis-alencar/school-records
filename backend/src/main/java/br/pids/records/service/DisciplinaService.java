package br.pids.records.service;

import java.util.Optional;


import br.pids.records.model.Curso;
import br.pids.records.model.Disciplina;
import br.pids.records.repository.CursoRepository;
import br.pids.records.repository.DisciplinaRepository;
import br.pids.records.service.exception.CursoInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Disciplina salvar(Disciplina disciplina) throws CursoInexistenteOuInativaException {
        Optional<Curso> curso = cursoRepository.findById(disciplina.getCurso().getCodigo());
        if (curso.isEmpty() || curso.get().isInativo()) {
            throw new CursoInexistenteOuInativaException();
        }

        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizar(Long codigo, Disciplina disciplina) throws CursoInexistenteOuInativaException {
        Disciplina disciplinaSalvo = buscarDisciplinaExistente(codigo);
        if (!disciplina.getCurso().equals(disciplinaSalvo.getCurso())) {
            validarDisciplina(disciplina);
        }

        BeanUtils.copyProperties(disciplina, disciplinaSalvo, "codigo");

        return disciplinaRepository.save(disciplinaSalvo);
    }

    private void validarDisciplina(Disciplina disciplina) throws CursoInexistenteOuInativaException {
        Optional<Curso> curso = null;
        if (disciplina.getCurso().getCodigo() != null) {
            curso = cursoRepository.findById(disciplina.getCurso().getCodigo());
        }

        if (curso.isEmpty() || curso.get().isInativo()) {
            throw new CursoInexistenteOuInativaException();
        }
    }

    private Disciplina buscarDisciplinaExistente(Long codigo) {

/* 		Optional<Disciplina> disciplinaSalvo = disciplinaRepository.findById(codigo);
		if (disciplinaSalvo.isEmpty()) {
			throw new IllegalArgumentException();
		} */
        return disciplinaRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
    }

}

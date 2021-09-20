package br.pids.records.service;


import br.pids.records.model.Curso;
import br.pids.records.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso atualizar(Long codigo, Curso curso) {
        Curso cursoSalva = buscarCursosPeloCodigo(codigo);

        BeanUtils.copyProperties(curso, cursoSalva, "codigo");
        return cursoRepository.save(cursoSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Curso cursoSalva = buscarCursosPeloCodigo(codigo);
        cursoSalva.setAtivo(ativo);
        cursoRepository.save(cursoSalva);
    }

    private Curso buscarCursosPeloCodigo(Long codigo) {
        Curso cursoSalva =  cursoRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return cursoSalva;
    }
}

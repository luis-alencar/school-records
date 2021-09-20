package br.pids.records.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.pids.records.repository.projection.ResumoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pids.records.event.RecursoCriadoEvent;
import br.pids.records.model.Curso;
import br.pids.records.repository.CursoRepository;
import br.pids.records.repository.filter.CursoFilter;
import br.pids.records.service.CursoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/curso")
public class CursoResource {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @ApiOperation(value = "Pesquisar um curso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso encontrado "),
            @ApiResponse(code = 404, message = "Curso Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public Page<Curso> pesquisar(CursoFilter cursoFilter, Pageable pageable) {
        return cursoRepository.filtrar(cursoFilter, pageable);
    }

    @ApiOperation(value = "Listar resumida de uma lista de cursos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resumo de cursos "),
            @ApiResponse(code = 404, message = "Não foi possivel listar resumo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(params = "resumo")
    public Page<ResumoCurso> resumir(CursoFilter cursoFilter, Pageable pageable) {
        return cursoRepository.resumir(cursoFilter, pageable);
    }

    @ApiOperation(value = "Registrar um novo curso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro realizado com sucesso "),
            @ApiResponse(code = 404, message = "Erro ao registrar um curso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<Curso> criar(@Valid @RequestBody Curso curso, HttpServletResponse response) {
        Curso cursoSalva = cursoRepository.save(curso);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, cursoSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalva);
    }

    @ApiOperation(value = "Listar um curso por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar curso/codigo com sucesso "),
            @ApiResponse(code = 404, message = "Erro listar curso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<Curso> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Curso> curso = cursoRepository.findById(codigo);
        return (ResponseEntity<Curso>) (curso.isPresent() ? ResponseEntity.ok(curso.get()) : ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Deletar um curso por codigo")
    @DeleteMapping("/{codigo}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Curso/codigo deletado com sucesso "),
            @ApiResponse(code = 404, message = "Erro ao deletar curso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        this.cursoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizar curso/codigo com sucesso"),
            @ApiResponse(code = 404, message = "Curso Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation(value = "Atualizar um curso por codigo")
    public ResponseEntity<Curso> atualizar(@PathVariable Long codigo, @Valid @RequestBody Curso curso) {
        Curso cursoSalva = cursoService.atualizar(codigo, curso);
        return ResponseEntity.ok(cursoSalva);
    }

    @ApiOperation(value = "Atualizar status curso por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Status atualizado curso"),
            @ApiResponse(code = 400, message = "Erro ao atuzalizar o status do curso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{codigo}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        cursoService.atualizarPropriedadeAtivo(codigo, ativo);
    }
}

package br.pids.records.resource;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pids.records.event.RecursoCriadoEvent;
import br.pids.records.exceptionHandler.SchoolRecordsExceptionHandler.Erro;
import br.pids.records.model.Disciplina;
import br.pids.records.repository.DisciplinaRepository;
import br.pids.records.service.DisciplinaService;
import br.pids.records.service.exception.CursoInexistenteOuInativaException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/disciplina")
public class DisciplinaResource {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @ApiOperation(value = "Listar uma lista de disciplinas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar disciplina com sucesso"),
            @ApiResponse(code = 404, message = "Erro ao listar disciplina"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }


    @ApiOperation(value = "Listar um disciplina por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar disciplina com sucesso"),
            @ApiResponse(code = 404, message = "Erro ao listar disciplina"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<Disciplina> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(codigo);
        return (ResponseEntity<Disciplina>) (disciplina.isPresent() ? ResponseEntity.ok(disciplina.get()) : ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registrar uma disciplina")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registro realizado com sucesso"),
            @ApiResponse(code = 404, message = "Erro ao registrar uma disciplina"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<Disciplina> criar(@Valid @RequestBody Disciplina disciplina, HttpServletResponse response) throws CursoInexistenteOuInativaException {
        Disciplina disciplinaSalvo = disciplinaService.salvar(disciplina);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, disciplinaSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaSalvo);
    }

    @ApiOperation(value = "Deletar um disciplina por codigo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deletar deletada com sucesso"),
            @ApiResponse(code = 404, message = "Erro ao deletar uma disciplina"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        this.disciplinaRepository.deleteById(codigo);
    }


    @ExceptionHandler({ CursoInexistenteOuInativaException.class })
    public ResponseEntity<Object> handleCursoInexistenteOuInativaException(CursoInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("curso.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualizar disciplina/codigo com sucesso"),
            @ApiResponse(code = 404, message = "Disciplina Não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation(value = "Atualizar um disciplina por codigo")
    @PutMapping("/{codigo}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long codigo, @Valid @RequestBody Disciplina disciplina) {
        try {
            Disciplina disciplinaSalvo = disciplinaService.atualizar(codigo, disciplina);
            return ResponseEntity.ok(disciplinaSalvo);
        } catch (IllegalArgumentException | CursoInexistenteOuInativaException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

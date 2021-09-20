package br.pids.records.repository.curso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.pids.records.model.Curso_;
import br.pids.records.repository.projection.ResumoCurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import br.pids.records.model.Curso;
import br.pids.records.repository.filter.CursoFilter;

public class CursoRepositoryImpl implements CursoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
		Root<Curso> root = criteria.from(Curso.class);
		
		Predicate[] predicates = criarRestricoes(cursoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Curso> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(cursoFilter));
	}

   

    private Predicate[] criarRestricoes(CursoFilter cursoFilter, CriteriaBuilder builder, Root<Curso> root) {
		List<Predicate> predicates = new ArrayList<>();
				
		if(!ObjectUtils.isEmpty(cursoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Curso_.descricao)), "%" + cursoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if(!ObjectUtils.isEmpty(cursoFilter.getLocalidade())) {
			predicates.add(builder.like(
					builder.lower(root.get(Curso_.localidade)), "%" + cursoFilter.getLocalidade().toLowerCase() + "%"));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	
	
	private Long total(CursoFilter cursoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Curso> root = criteria.from(Curso.class);
		
		Predicate[] predicates = criarRestricoes(cursoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}



	@Override
	public Page<ResumoCurso> resumir(CursoFilter cursoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoCurso> criteria = builder.createQuery(ResumoCurso.class);
		Root<Curso> root = criteria.from(Curso.class);
		
		criteria.select(builder.construct(ResumoCurso.class
				, root.get(Curso_.codigo), root.get(Curso_.localidade)
				, root.get(Curso_.dataCadastro), root.get(Curso_.descricao)
				, root.get(Curso_.ativo)));
		
		Predicate[] predicates = criarRestricoes(cursoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoCurso> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(cursoFilter));
		
	}
}

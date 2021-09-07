package br.pids.records.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pids.records.dto.AlunoDTO;
import br.pids.records.model.Aluno;
import br.pids.records.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	@Transactional(readOnly = true)
	public List<AlunoDTO> findAll(){
		List<Aluno> result = repository.findAll();
		return result.stream().map(x -> new AlunoDTO(x)).collect(Collectors.toList());
	}
}

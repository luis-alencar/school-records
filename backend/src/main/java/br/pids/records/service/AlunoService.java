package br.pids.records.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pids.records.dto.AlunoDTO;
import br.pids.records.model.Aluno;
import br.pids.records.repositories.AlunoRepository;
import br.pids.records.service.exception.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	@Transactional(readOnly = true)
	public List<AlunoDTO> findAll(){
		List<Aluno> result = repository.findAll();
		return result.stream().map(x -> new AlunoDTO(x)).collect(Collectors.toList());
	}
	
	public Aluno findById(Long id) {
		Optional<Aluno> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
}

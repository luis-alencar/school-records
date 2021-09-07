package br.pids.records.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pids.records.dto.EmpresaDTO;
import br.pids.records.model.Empresa;
import br.pids.records.repositories.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repository;
	
	@Transactional(readOnly = true)
	public List<EmpresaDTO> findAll(){
		List<Empresa> result = repository.findAll();
		return result.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
	}
}

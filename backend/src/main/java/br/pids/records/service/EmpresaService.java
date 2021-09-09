package br.pids.records.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pids.records.dto.EmpresaDTO;
import br.pids.records.model.Empresa;
import br.pids.records.repositories.EmpresaRepository;
import br.pids.records.service.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repository;
	
	@Transactional(readOnly = true)
	public List<EmpresaDTO> findAll(){
		List<Empresa> result = repository.findAll();
		return result.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
	}
	
	public Empresa findById(Long id) {
		Optional<Empresa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Empresa insertEmpresa(Empresa obj) {
		return repository.save(obj);
	}
	
	public Empresa fromDTO(EmpresaDTO objDto) {
		return new Empresa(objDto.getId(), objDto.getNome(), objDto.getProjeto());
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	@Transactional
	public EmpresaDTO update(Long id, EmpresaDTO dto) {
		try {
		Empresa entity = repository.getOne(id);
		entity.setNome(dto.getNome());
		entity.setProjeto(dto.getProjeto());
		return new EmpresaDTO(entity);
		}catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
	}

}

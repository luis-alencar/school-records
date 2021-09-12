package br.pids.records.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pids.records.dto.ComentarioDTO;
import br.pids.records.model.Comentario;
import br.pids.records.repositories.ComentarioRepository;
import br.pids.records.service.exception.ObjectNotFoundException;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ComentarioDTO> findAll(Pageable pageable){
		Page<Comentario> result = repository.findAll(pageable);
		return result.map(x -> new ComentarioDTO(x));
	}
	
	public Comentario findById(Long id) {
		Optional<Comentario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
}

package br.pids.records.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pids.records.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	

}

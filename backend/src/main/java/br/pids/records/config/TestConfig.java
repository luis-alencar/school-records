package br.pids.records.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.pids.records.model.Empresa;
import br.pids.records.repositories.EmpresaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	//@Autowired
	//private AlunoRepository alunoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Empresa e1 = new Empresa(null, "Grupo 1", "Projeto Grupo 1");
		Empresa e2 = new Empresa(null, "Grupo 2", "Projeto Grupo 2");
		Empresa e3 = new Empresa(null, "Grupo 3", "Projeto Grupo 3");
		Empresa e4 = new Empresa(null, "Grupo 4", "Projeto Grupo 4");
		Empresa e5 = new Empresa(null, "Grupo 5", "Projeto Grupo 5");
		
		empresaRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
		
		/*
		Aluno a1 = new Aluno(null, "Primeiro aluno", "email1@teste.com", 14703692, new EmpresaDTO(e1.getId()));
		Aluno a2 = new Aluno(null, "Segundo aluno", "email2@teste.com", 25814703, new EmpresaDTO(e2.getId()));
		Aluno a3 = new Aluno(null, "Terceiro aluno", "email3@teste.com", 36925814, ew EmpresaDTO(e2);
		alunoRepository.saveAll(Arrays.asList(a1,a2,a3));
		
		*/
		
		
	}

}

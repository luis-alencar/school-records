package br.pids.records.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	//@Autowired
	//private EmpresaRepository empresaRepository;
	
	//@Autowired
	//private AlunoRepository alunoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		/*
		Aluno a1 = new Aluno(null, "Nome aluno", 98658745);
		alunoRepository.saveAll(Arrays.asList(a1));
		
		Empresa e1 = new Empresa(null, "Grupo 1", "Projeto Grupo 1");
		empresaRepository.saveAll(Arrays.asList(u1));*/
		
	}

}

CREATE TABLE tb_alunos (
	codigo BIGSERIAL PRIMARY KEY  NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE,
	nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11),
    email VARCHAR(100),
	status VARCHAR(1) NOT NULL
);

CREATE TABLE tb_alunos_as_turmas (
	codigo_alunos BIGSERIAL NOT NULL,
	codigo_turmas BIGSERIAL NOT NULL,
	PRIMARY KEY (codigo_alunos, codigo_turmas),
	CONSTRAINT fk_alunos_t FOREIGN KEY(codigo_alunos) REFERENCES tb_alunos(codigo),
	CONSTRAINT fk_turmas_t FOREIGN KEY(codigo_turmas) REFERENCES tb_turmas(codigo)
);


INSERT INTO tb_alunos(codigo, nome, telefone, email, status) VALUES (1,  'Nilza Eleusa Pereira de Abreu', '99999999999','nilzaueg@gmail.com', 'A');

INSERT INTO tb_alunos_as_turmas(codigo_alunos, codigo_turmas) VALUES (1,1);


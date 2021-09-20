export class CursosFiltro {
  descricao: string;
  localidade: string;
  pagina = 0;
  itensPorPagina = 5;
}

export class Curso {
  codigo: number;
  descricao: string;
  localidade: string;
  dataCadastro: Date;
  ativo = true;
}


export class DisciplinasFiltro {
  descricao: string;
  curso = new Curso();
  pagina = 0;
  itensPorPagina = 5;
}

export class Disciplina {
  codigo: number;
  curso = new Curso();
  descricao: string;
  dataCadastro: Date;
  ativo = true;
}


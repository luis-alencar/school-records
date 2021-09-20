package br.pids.records.repository.projection;

import java.time.LocalDate;

public class ResumoCurso {
    private Long codigo;
    private String descricao;
    private LocalDate dataCadastro;
    private String localidade;
    private Boolean ativo;

    public ResumoCurso(Long codigo, String descricao, LocalDate dataCadastro, String localidade, Boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.localidade = localidade;
        this.ativo = ativo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

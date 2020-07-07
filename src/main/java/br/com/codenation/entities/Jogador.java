package br.com.codenation.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Jogador {

    private Long id;                        //* Identificador do Jogador
    private Long idTime;                    //* Identificador do time
    private String nome;                    //* Nome do Jogador
    private LocalDate dataNascimento;       //* Data de nascimento do Jogador
    private Integer nivelHabilidade;        //* Nível de habilidade do jogador (0 a 100)
    private BigDecimal salario;             //* Salário do jogador
    private Boolean capitao = false;

    public Jogador() {}

    public Jogador(Long id, long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    public Jogador builder(Long id, long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        return new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Boolean getCapitao() {
        return capitao;
    }

    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return getId().equals(jogador.getId()) &&
                Objects.equals(getIdTime(), jogador.getIdTime()) &&
                Objects.equals(getNome(), jogador.getNome()) &&
                Objects.equals(getDataNascimento(), jogador.getDataNascimento()) &&
                Objects.equals(getNivelHabilidade(), jogador.getNivelHabilidade()) &&
                Objects.equals(getSalario(), jogador.getSalario()) &&
                Objects.equals(getCapitao(), jogador.getCapitao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdTime(), getNome(), getDataNascimento(), getNivelHabilidade(), getSalario(), getCapitao());
    }
}

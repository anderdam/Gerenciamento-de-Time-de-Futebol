package br.com.codenation.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe para a criação do objeto Meu Time
 */
public class MeuTime {
    private Long id;                        //* Identificador do Time
    private String nome;                    //* Nome do Time
    private LocalDate dataCriacao;          //* Data de criação do time
    private String corUniformePrincipal;    //* Cor do uniforme principal do time
    private String corUniformeSecundario;   //* Cor do uniforme secundário do time
    private List<Jogador> jogadores = new ArrayList<>();        //  Lista de jogadores no time

    public MeuTime() {}

    public MeuTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }    

    public MeuTime builder(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        return new MeuTime(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeuTime)) return false;
        MeuTime time = (MeuTime) o;
        return getId().equals(time.getId()) &&
                Objects.equals(getNome(), time.getNome()) &&
                Objects.equals(getDataCriacao(), time.getDataCriacao()) &&
                Objects.equals(getCorUniformePrincipal(), time.getCorUniformePrincipal()) &&
                Objects.equals(getCorUniformeSecundario(), time.getCorUniformeSecundario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDataCriacao(), getCorUniformePrincipal(), getCorUniformeSecundario(), getJogadores());
    }
}

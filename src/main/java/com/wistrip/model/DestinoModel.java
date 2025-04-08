package com.wistrip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "tb_destinos")
public class DestinoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(length = 100)
    private String descricao;

    @Column(length = 100)
    private String interesses;

    //Relacionamento com Acomodação
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destino", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("destino")
    private List<AcomodacaoModel> acomodacao;

    //Relacionameto com Viagem
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destino", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("destino")
    private List<ViagemModel> viagem;

    public List<AcomodacaoModel> getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(List<AcomodacaoModel> acomodacao) {
        this.acomodacao = acomodacao;
    }

    public List<ViagemModel> getViagem() {
        return viagem;
    }

    public void setViagem(List<ViagemModel> viagem) {
        this.viagem = viagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInteresses() {
        return interesses;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }
}
